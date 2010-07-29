package com.tda.presentation.client.datasource;

import java.util.List;

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
import com.tda.model.item.ItemBuilder;
import com.tda.model.item.MeasureUnit;
import com.tda.presentation.client.exception.NotInitializedException;
import com.tda.presentation.client.service.CrudServiceGWTWrapperAsync;

/**
 * Example <code>GwtRpcDataSource</code> implementation.
 * 
 * @author Aleksandras Novikovas
 * @author System Tier
 * @version 1.0
 */
public class ItemGwtRPCDS extends CrudGwtRPCDS<Item> {

	private static CrudServiceGWTWrapperAsync<Item> rpc;
	private static ItemGwtRPCDS _instance;

	private static final String QUANTITY = "quantity";
	private static final String CATEGORY = "category";
	private static final String MEASURE = "measure";
	private static final String DESCRIPTION = "description";
	private static final String NAME = "name";
	private static final String ID = "id";

	public static ItemGwtRPCDS getInstance() throws NotInitializedException {
		if (_instance == null)
			throw new NotInitializedException("ItemGwtRPCDS");

		return _instance;
	}

	public static void setRpc(CrudServiceGWTWrapperAsync<Item> service) {
		if (_instance == null) {
			rpc = service;
			_instance = new ItemGwtRPCDS(rpc);
		}
	}

	private ItemGwtRPCDS(CrudServiceGWTWrapperAsync<Item> rpc) {
		setID("ItemsDataSource");

		DataSourceIntegerField idField = new DataSourceIntegerField(ID, "Id");
		idField.setType(FieldType.SEQUENCE);
		idField.setPrimaryKey(true);
		idField.setHidden(true);
		addField(idField);

		DataSourceTextField nameField = new DataSourceTextField(NAME, "Nombre");
		nameField.setRequired(true);
		nameField.setType(FieldType.TEXT);
		addField(nameField);

		DataSourceTextField descriptionField = new DataSourceTextField(
				DESCRIPTION, "Descripción");
		descriptionField.setRequired(false);
		descriptionField.setType(FieldType.TEXT);
		TextAreaItem textAreaItem = new TextAreaItem();
		textAreaItem.setHeight(70);
		descriptionField.setEditorType(textAreaItem);
		addField(descriptionField);

		DataSourceIntegerField quantityField = new DataSourceIntegerField(
				QUANTITY, "Cantidad");
		quantityField.setRequired(true);
		quantityField.setType(FieldType.INTEGER);
		addField(quantityField);

		DataSourceEnumField categoryField = new DataSourceEnumField(CATEGORY,
				"Categoría");
		categoryField.setRequired(true);
		categoryField.setType(FieldType.ENUM);
		categoryField.setValueMap(Category.getMap());
		addField(categoryField);

		DataSourceEnumField measureField = new DataSourceEnumField(MEASURE,
				"Medida");
		measureField.setRequired(true);
		measureField.setType(FieldType.ENUM);
		SelectItem measureSelect = new SelectItem();
		measureSelect.setValueMap(MeasureUnit.getMap());
		measureField.setEditorType(measureSelect);
		addField(measureField);
	}

	@Override
	public void copyValues(Record record, DynamicForm form) {
		form.setValue(ID, record.getAttribute(ID));
		form.setValue(NAME, record.getAttribute(NAME));
		form.setValue(DESCRIPTION, record.getAttribute(DESCRIPTION));
		form.setValue(MEASURE, MeasureUnit.getKey(record.getAttribute(MEASURE)));
		form.setValue(CATEGORY, Category.getKey(record.getAttribute(CATEGORY)));
		form.setValue(QUANTITY, record.getAttribute(QUANTITY));
	}

	@Override
	protected void executeFetch(final String requestId,
			final DSRequest request, final DSResponse response) {
		// This can be used as parameter for server side sorting.
		// request.getSortBy ();

		// Finding which rows were requested
		// Normally these two indexes should be passed to server
		// but for this example I will do "paging" on client side
		final int startIndex = (request.getStartRow() < 0) ? 0 : request
				.getStartRow();
		final int endIndex = (request.getEndRow() == null) ? -1 : request
				.getEndRow();

		rpc.findAll(new AsyncCallback<List<Item>>() {
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

	@Override
	protected void copyValues(Item from, ListGridRecord to) {

		to.setAttribute(ID, from.getId().toString());
		to.setAttribute(NAME, from.getName());

		if (from.getDescription() != null) {
			to.setAttribute(DESCRIPTION, from.getDescription());
		}

		if (from.getQuantity() != null) {
			to.setAttribute(QUANTITY, from.getQuantity().toString());
		}

		if (from.getMeasureUnit() != null) {
			to.setAttribute(MEASURE, MeasureUnit.getName(from.getMeasureUnit()));
		}

		if (from.getCategory() != null) {
			to.setAttribute(CATEGORY, Category.getName(from.getCategory()));
		}
	}

	@Override
	protected CrudServiceGWTWrapperAsync<Item> getRpc() {
		return rpc;
	}

	@Override
	public Item get(DynamicForm form) {

		Item item = ItemBuilder
				.createItem()
				.withName(form.getValueAsString(NAME))
				.withCategory(Category.valueOf(form.getValueAsString(CATEGORY)))
				.withMeasureUnit(
						MeasureUnit.valueOf(form.getValueAsString(MEASURE)))
				.withDescription(form.getValueAsString(DESCRIPTION))
				.withQuantity(Long.valueOf(form.getValueAsString(QUANTITY)))
				.build();

		String idString = form.getValueAsString(ID);
		if (idString != null) {
			item.setId(Long.valueOf(idString));
		}

		return item;
	}
}
