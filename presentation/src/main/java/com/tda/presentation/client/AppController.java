package com.tda.presentation.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.tda.presentation.client.event.AddItemEvent;
import com.tda.presentation.client.event.AddItemEventHandler;
import com.tda.presentation.client.event.NewItemEvent;
import com.tda.presentation.client.event.NewItemEventHandler;
import com.tda.presentation.client.presenter.AddItemPresenter;
import com.tda.presentation.client.presenter.ItemPresenter;
import com.tda.presentation.client.presenter.Presenter;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;
import com.tda.presentation.client.view.AddItemView;
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
		eventBus.addHandler(AddItemEvent.TYPE, new AddItemEventHandler() {

			public void onAddItem(AddItemEvent event) {
				doAddNewItem();
			}
		});

		eventBus.addHandler(NewItemEvent.TYPE, new NewItemEventHandler() {

			public void onNewItem(NewItemEvent event) {
				doOnNewItem();
			}
		});
	}

	private void doOnNewItem() {
		History.newItem("itemsList");
	}

	private void doAddNewItem() {
		History.newItem("addItem");
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
