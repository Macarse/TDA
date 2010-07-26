package com.tda.presentation.client.datasource;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.fields.DataSourceEnumField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.tda.model.item.Category;
import com.tda.model.item.Item;
import com.tda.model.item.MeasureUnit;
import com.tda.presentation.client.service.ItemServiceGWTWrapper;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;


/**
 * Example <code>GwtRpcDataSource</code> implementation.
 * 
 * @author Aleksandras Novikovas
 * @author System Tier
 * @version 1.0
 */
public class ItemGwtRPCDS extends GwtRpcDataSource {

	private static ItemGwtRPCDS _instance;

	public static ItemGwtRPCDS getInstance() {
		if ( _instance == null )
			_instance = new ItemGwtRPCDS();

		return _instance;
	}

	private ItemGwtRPCDS() {
		setID("ItemsDataSource");

		DataSourceIntegerField idField = new DataSourceIntegerField("id", "Id");
		idField.setType(FieldType.SEQUENCE);
		idField.setPrimaryKey(true);
		idField.setHidden(true);
		addField(idField);

		DataSourceTextField nameField = new DataSourceTextField("name", "Nombre");
		nameField.setRequired(true);
		nameField.setType(FieldType.TEXT);
		addField(nameField);

		DataSourceTextField descriptionField = new DataSourceTextField("description", "Descripción", 200);
		descriptionField.setRequired(false);
		descriptionField.setType(FieldType.TEXT);
		TextAreaItem textAreaItem = new TextAreaItem();
        textAreaItem.setHeight(70);
		descriptionField.setEditorType(textAreaItem);
		addField(descriptionField);

		DataSourceIntegerField quantityField = new DataSourceIntegerField("quantity", "Cantidad");
		quantityField.setRequired(true);
		quantityField.setType(FieldType.INTEGER);
		addField(quantityField);


		DataSourceEnumField categoryField = new DataSourceEnumField("category", "Categoría");
		categoryField.setRequired(true);
		categoryField.setType(FieldType.ENUM);
		SelectItem categorySelect = new SelectItem();
		categorySelect.setDefaultValue(Category.medical.toString());
		categorySelect.setValueMap(Category.getMap());
		categoryField.setEditorType(categorySelect);
		addField(categoryField);

		DataSourceEnumField measureField = new DataSourceEnumField("measure", "Medida");
		measureField.setRequired(true);
		measureField.setType(FieldType.ENUM);
		SelectItem measureSelect = new SelectItem();
		measureSelect.setValueMap(MeasureUnit.getMap());
		measureSelect.setDefaultValue(MeasureUnit.unit.toString());
		measureField.setEditorType(measureSelect);
		addField(measureField);
	}
	
	public static void copyValues(DynamicForm form, Record record){
		form.setValue("name", record.getAttribute("name"));
		form.setValue("id", record.getAttribute("id"));
	}
	
	public static void copyValues(Record record, DynamicForm form ){
		form.setValue("name", record.getAttribute("name"));
		form.setValue("id", record.getAttribute("id"));
	}
	
	public static Item getItem(DynamicForm form){
		Item item = new Item();

		item.setName(form.getValueAsString("name"));
		String idString = form.getValueAsString("id");
		if ( idString == null ) {
			idString = "1";
		}

		item.setId(Long.valueOf(idString));

		return item;
	}

	@Override
	protected void executeFetch(final String requestId,
			final DSRequest request, final DSResponse response) {
		// This can be used as parameter for server side sorting.
		// request.getSortBy ();

		// Finding which rows were requested
		// Normally these two indexes should be passed to server
		// but for this example I will do "paging" on client side
		final int startIndex = (request.getStartRow() < 0) ? 0 : request.getStartRow();
		final int endIndex = (request.getEndRow() == null) ? -1 : request.getEndRow();

		ItemServiceGWTWrapperAsync service = GWT.create(ItemServiceGWTWrapper.class);

		
		service.findAll(new AsyncCallback<List<Item>>() {
			public void onFailure(Throwable caught) {
				response.setStatus(RPCResponse.STATUS_FAILURE);
				processResponse(requestId, response);
			}

			public void onSuccess(List<Item> result) {
				// Calculating size of return list
				int size = result.size();
				if (endIndex >= 0) {
					if (endIndex < startIndex) {
						size = 0;
					} else {
						size = endIndex - startIndex + 1;
					}
				}
				// Create list for return - it is just requested records
				ListGridRecord[] list = new ListGridRecord[size];
				if (size > 0) {
					for (int i = 0; i < result.size(); i++) {
						if (i >= startIndex && i <= endIndex) {
							ListGridRecord record = new ListGridRecord();
							copyValues(result.get(i), record);
							list[i - startIndex] = record;
						}
					}
				}
				response.setData(list);
				// IMPORTANT: for paging to work we have to specify size of full
				// result set
				response.setTotalRows(result.size());
				processResponse(requestId, response);
			}
		});
	}

	@Override
	protected void executeAdd(final String requestId, final DSRequest request,
			final DSResponse response) {
	}

	@Override
	protected void executeUpdate(final String requestId,
			final DSRequest request, final DSResponse response) {
	}

	@Override
	protected void executeRemove(final String requestId,
			final DSRequest request, final DSResponse response) {
	}

	private static void copyValues(Item from, ListGridRecord to) {

		to.setAttribute("id", from.getId().toString());
		to.setAttribute("name", from.getName());

		if ( from.getDescription() != null ) {
			to.setAttribute("description", from.getDescription());
		}	

		if ( from.getQuantity() != null ) {
			to.setAttribute("quantity", from.getQuantity().toString());
		}

		if ( from.getMeasureUnit() != null ) {
			to.setAttribute("measure", from.getMeasureUnit().toString());
		}

		if ( from.getCategory() != null ) {
			to.setAttribute("category", from.getCategory().toString());
		}
	}
}
