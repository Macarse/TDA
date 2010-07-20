package com.tda.presentation.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.tda.model.Item;

public class ItemsViewImpl<T> extends Composite implements ItemsView<T> {

	@UiTemplate("ItemsView.ui.xml")
	interface ItemsViewUiBinder extends UiBinder<Widget, ItemsViewImpl> {
	}

	private static ItemsViewUiBinder uiBinder = GWT.create(ItemsViewUiBinder.class);

	private Presenter<T> presenter;
	private ListGrid listGrid;
	private List<T> rowData;

	@UiField 
	VerticalPanel itemsContainer;

	@UiField
	Button addButton;

	@UiField
	Button deleteButton;

	public ItemsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		listGrid = new ListGrid();
		listGrid.setWidth(750);  
		listGrid.setHeight(224);  
		listGrid.setHeaderHeight(40);
		listGrid.setDataSource(new ItemsDataSource());
        listGrid.setAutoFetchData(false);

		ListGridField idField = new ListGridField("id", "id");
		idField.setAlign(Alignment.CENTER);  
		idField.setType(ListGridFieldType.INTEGER);
		idField.setCanSort(true);

		ListGridField nameField = new ListGridField("name", "Nombre");
		nameField.setAlign(Alignment.CENTER);  
		nameField.setType(ListGridFieldType.SEQUENCE);
		nameField.setCanSort(true);

		listGrid.setFields(idField, nameField);
		
		itemsContainer.add(listGrid);

		
	}

	public void setPresenter(Presenter<T> presenter) {
		this.presenter = presenter;
	}

	public Widget asWidget() {
		return this;
	}

	public void setRowData(List<Item> items) {
		for (Item item : items) {
			ListGridRecord record = new ListGridRecord();
			record.setAttribute("id", item.getId());
			record.setAttribute("name", item.getName());		
			listGrid.addData(record);
		}
		listGrid.fetchData();
	}
}
