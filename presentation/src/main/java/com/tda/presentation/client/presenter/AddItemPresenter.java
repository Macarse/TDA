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
				final DynamicForm form = display.getForm();

				if ( form.validate() ) {
					System.out.println("Values added!");
					form.saveData();
					System.out.println("Form saved!");

					/* TODO: Create a new Item just to fire an Event.
					 * Change this! */
					final Item item = new Item();
					item.setName((String) form.getValue("name"));
					eventBus.fireEvent(new NewItemEvent(item));

					onDestroy();
					
//					
//					final Item item = new Item();
//					
//					/* Hack */
//					Integer id = (Integer) form.getValue("id");
//					item.setId(id.longValue());
//
//					item.setName((String) form.getValue("name"));
//
//					rpc.save(item, new AsyncCallback<Void>() {
//						
//						public void onSuccess(Void arg0) {
//							System.out.println("Saved in the db!");
//							eventBus.fireEvent(new NewItemEvent(item));
//							/* Update the data source */
//							form.saveData();
//							onDestroy();
//						}
//						
//						public void onFailure(Throwable e) {
//							/*TODO: Handle error */
//							display.getForm().setErrorsPreamble(e.getMessage());
//						}
//					});

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
