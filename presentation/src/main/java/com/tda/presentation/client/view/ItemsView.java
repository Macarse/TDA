package com.tda.presentation.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.tda.model.Item;
import com.tda.presentation.client.datasource.ItemGwtRPCDS;
import com.tda.presentation.client.presenter.ItemPresenter;

public class ItemsView extends Composite implements ItemPresenter.Display {

	@UiTemplate("ItemsView.ui.xml")
	interface ItemsViewUiBinder extends UiBinder<Widget, ItemsView> {
	}

	private static ItemsViewUiBinder uiBinder = GWT
			.create(ItemsViewUiBinder.class);

	@UiField
	VerticalPanel itemsContainer;

	@UiField
	Button addButton;

	@UiField
	Button deleteButton;

	ListGrid listGrid;

	public ItemsView() {
		initWidget(uiBinder.createAndBindUi(this));

		listGrid = new ListGrid();
		listGrid.setWidth(750);
		listGrid.setHeight(224);
		listGrid.setHeaderHeight(40);
		listGrid.setDataSource(ItemGwtRPCDS.getInstance());
		listGrid.setAutoFetchData(true);

		itemsContainer.add(listGrid);

		/* TODO: REMOVE THIS */
		deleteButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent arg0) {
				listGrid.fetchData();
			}
		});
	}

	public HasClickHandlers getAddButton() {
		return addButton;
	}

	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}

	public ListGrid getGrid() {
		return listGrid;
	}

	public void setData(List<Item> data) {
		for (Item item : data) {

			ListGridRecord record = new ListGridRecord();
			record.setAttribute("id", item.getId().toString());
			record.setAttribute("name", item.getName());
			ItemGwtRPCDS.getInstance().addData(record);
		}
	}

	public ListGridRecord getClickedRow(ClickEvent e) {
		return listGrid.getSelectedRecord();
	}

	public ListGridRecord[] getSelectedRows() {
		return listGrid.getSelection();
	}

	public Widget asWidget() {
		return this;
	}
}
