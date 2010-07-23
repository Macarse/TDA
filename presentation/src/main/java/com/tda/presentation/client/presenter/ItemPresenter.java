package com.tda.presentation.client.presenter;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.tda.model.Item;
import com.tda.presentation.client.event.AddItemEvent;
import com.tda.presentation.client.event.EditItemEvent;
import com.tda.presentation.client.event.NewItemEvent;
import com.tda.presentation.client.event.NewItemEventHandler;
import com.tda.presentation.client.event.RemoveItemEvent;
import com.tda.presentation.client.event.RemovedItemEvent;
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
		
		display.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//eventBus.fireEvent(new RemoveItemEvent(Long.valueOf(display.getClickedRow().getAttribute("id"))));
				SC.ask("Desea borrar el item?", new BooleanCallback() {
					public void execute(Boolean value) {
						if (value){
							long id = Long.valueOf(display.getClickedRow().getAttribute("id"));
							removeItem(id);
						}
					}
				});
			}
		});
	}
	
	/*
	 * TODO: MEJORAR!!!!!!!!!
	 */
	private void removeItem(long id){
		/*
		 * First got to fetch item(id)
		 */
		rpc.findById(id, new AsyncCallback<Item>() {
			public void onFailure(Throwable arg0) {
				SC.say("Error al retribuir el item");
			}

			public void onSuccess(Item item) {
				rpc.delete(item, new AsyncCallback<Void>(){

					public void onFailure(Throwable arg0) {
						SC.say("Error al eliminar el item");
					}

					public void onSuccess(Void arg0) {
						//fire event
						eventBus.fireEvent(new RemovedItemEvent());
					}
					
				});
			}
		});
	}

	public void onDestroy() {
		handlerRegistration.removeHandler();
	}
}
