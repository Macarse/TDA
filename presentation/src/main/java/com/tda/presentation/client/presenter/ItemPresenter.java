package com.tda.presentation.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.tda.model.Item;
import com.tda.presentation.client.event.AddItemEvent;
import com.tda.presentation.client.event.EditItemEvent;
import com.tda.presentation.client.event.NewItemEvent;
import com.tda.presentation.client.event.NewItemEventHandler;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;

public class ItemPresenter implements Presenter {

	/*
	 * TODO: ListGridRecord MUST be a Record
	 */
	public interface Display {
		HasClickHandlers getAddButton();
		HasClickHandlers getEditButton();
		HasClickHandlers getDeleteButton();
		ListGrid getGrid();
		void setData(List<Item> data);
		Record getClickedRow();
		Record[] getSelectedRows();
		Widget asWidget();
	}

	private final Display display;
	private final ItemServiceGWTWrapperAsync rpc;
	private final HandlerManager eventBus;
	private HandlerRegistration handlerRegistration;

	public ItemPresenter(ItemServiceGWTWrapperAsync rpc, HandlerManager eventBus, Display view) {
		this.display = view;
		this.rpc = rpc;
		this.eventBus = eventBus;
	}

	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
		handlerRegistration = eventBus.addHandler(NewItemEvent.TYPE, new NewItemEventHandler() {
			public void onNewItem(NewItemEvent event) {
				System.out.println("ItemPresenter: new Item! from " + this);
				Window.alert("Se agrego un item");
			}
		});
		
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new AddItemEvent());
			}
		});
		
		display.getEditButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditItemEvent(Long.valueOf(display.getClickedRow().getAttribute("id"))));
			}
		});
	}

	public void onDestroy() {
		handlerRegistration.removeHandler();
	}
}
