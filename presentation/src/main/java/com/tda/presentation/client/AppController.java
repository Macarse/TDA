package com.tda.presentation.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.tda.presentation.client.event.AddItemEvent;
import com.tda.presentation.client.event.AddItemEventHandler;
import com.tda.presentation.client.event.EditItemEvent;
import com.tda.presentation.client.event.EditItemEventHandler;
import com.tda.presentation.client.event.EditedItemEvent;
import com.tda.presentation.client.event.EditedItemEventHandler;
import com.tda.presentation.client.event.NewItemEvent;
import com.tda.presentation.client.event.NewItemEventHandler;
import com.tda.presentation.client.event.RemoveItemEvent;
import com.tda.presentation.client.event.RemoveItemEventHandler;
import com.tda.presentation.client.event.RemovedItemEvent;
import com.tda.presentation.client.event.RemovedItemEventHandler;
import com.tda.presentation.client.presenter.AddItemPresenter;
import com.tda.presentation.client.presenter.EditItemPresenter;
import com.tda.presentation.client.presenter.ItemPresenter;
import com.tda.presentation.client.presenter.Presenter;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;
import com.tda.presentation.client.view.AddItemView;
import com.tda.presentation.client.view.EditItemView;
import com.tda.presentation.client.view.ItemsView;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final ItemServiceGWTWrapperAsync rpcService;
	private HasWidgets container;
	private Presenter presenter;

	public AppController(ItemServiceGWTWrapperAsync rpcService,
			HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);
		
		/*
		 * Handler for new item
		 */
		eventBus.addHandler(AddItemEvent.TYPE, new AddItemEventHandler() {
			public void onAddItem(AddItemEvent event) {
				doAddNewItem();
			}
		});

		/*
		 * Handler after an item was added
		 */
		eventBus.addHandler(NewItemEvent.TYPE, new NewItemEventHandler() {
			public void onNewItem(NewItemEvent event) {
				showList();
			}
		});
		
		/*
		 * Handler after an item was edited
		 */
		eventBus.addHandler(EditedItemEvent.TYPE, new EditedItemEventHandler() {
			public void onEditedItem(EditedItemEvent event) {
				showList();
			}
		});
		
		/*
		 * Handler for edit item
		 */
		eventBus.addHandler(EditItemEvent.TYPE, new EditItemEventHandler() {
			public void onEditItem(EditItemEvent event) {
				doEditItem(event.getId());
			}
		});
		
		/*
		 * Handler for remove item
		 */
		eventBus.addHandler(RemoveItemEvent.TYPE, new RemoveItemEventHandler() {
			public void onEvent(RemoveItemEvent event) {
				doRemoveItem(event.getId());
			}
		});
		
		/*
		 * Handler after an item was removed
		 */
		eventBus.addHandler(RemovedItemEvent.TYPE, new RemovedItemEventHandler() {
			public void onEvent(RemovedItemEvent event) {
				//total HACK
				History.newItem("removeItem");
			}
		});
		
	}
	
	private void doRemoveItem(long id){
		
	}
	
	private void showList(){
		History.newItem("itemsList");
	}

	private void doAddNewItem() {
		History.newItem("addItem");
	}
	
	private void doEditItem(long id) {
		History.newItem("editItem",false);
		Presenter presenter = new EditItemPresenter(rpcService, eventBus, new EditItemView(), id);
	    presenter.go(container);
	}

	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("itemsList");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			if (presenter != null) {
				presenter.onDestroy();
				presenter = null;
			}

			if (token.equals("itemsList")) {
				presenter = new ItemPresenter(rpcService, eventBus,
						new ItemsView());

			} else if (token.equals("addItem")) {
				presenter = new AddItemPresenter(rpcService, eventBus,
						new AddItemView());
			} else if (token.equals("editItem")) {
				// si se quiere hacer que cuando haga edit 
				// sin item seleccionado muestre algo
			} else if (token.equals("removeItem")) {
				// TODO: sacar hack
				presenter = new ItemPresenter(rpcService, eventBus,
						new ItemsView());
			}

			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

	public void onDestroy() {
		/* Do nothing */
	}
}
