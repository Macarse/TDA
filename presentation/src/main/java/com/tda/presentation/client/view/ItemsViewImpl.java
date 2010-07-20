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

	private ItemsDataSource itemsDataSouce;

	public ItemsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

		itemsDataSouce = new ItemsDataSource();

		listGrid = new ListGrid();
		listGrid.setWidth(750);  
		listGrid.setHeight(224);  
		listGrid.setHeaderHeight(40);
		listGrid.setDataSource(itemsDataSouce);
        listGrid.setAutoFetchData(true);

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
			System.out.println( "asdasdasd " + item.getId());
			ListGridRecord record = new ListGridRecord();
			record.setAttribute("id", item.getId().toString());
			record.setAttribute("name", item.getName());
			itemsDataSouce.addData(record);
		}

		listGrid.refreshFields();
	}
}
