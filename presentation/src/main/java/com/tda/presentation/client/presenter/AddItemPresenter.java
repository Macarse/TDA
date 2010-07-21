package com.tda.presentation.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.HasClickHandlers;
import com.tda.model.Item;
import com.tda.presentation.client.event.NewItemEvent;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;

public class AddItemPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getSubmitButton();
		Widget asWidget();
		Window getWindow();
		DynamicForm getForm();
		void onDestroy();
	}

	private final Display display;
	private final ItemServiceGWTWrapperAsync rpc;
	private final HandlerManager eventBus;

	public AddItemPresenter(ItemServiceGWTWrapperAsync rpc, HandlerManager eventBus, Display view) {
		this.display = view;
		this.rpc = rpc;
		this.eventBus = eventBus;
		bind();
	}

	private void bind() {
		display.getSubmitButton().addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if ( display.getForm().validate() ) {
					System.out.println("Values added!");
					Item item = new Item();
					item.setName("Pablo");
					eventBus.fireEvent(new NewItemEvent(item));
					onDestroy();
				} else {
					/* Do nothing */
				}
			}
		});

	}

	public void go(HasWidgets container) {
		container.add(display.asWidget());
	}

	public void onDestroy() {
		display.onDestroy();
	}
}
