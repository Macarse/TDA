package com.tda.presentation.client.presenter;

import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.core.DataClass;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.HasClickHandlers;
import com.tda.model.Item;
import com.tda.presentation.client.datasource.ItemDS;
import com.tda.presentation.client.event.EditedItemEvent;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;

public class EditItemPresenter implements Presenter {

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

	public EditItemPresenter(ItemServiceGWTWrapperAsync rpc, HandlerManager eventBus, Display view, long itemId) {
		this.display = view;
		this.rpc = rpc;
		this.eventBus = eventBus;
		bind();
		setItemForm(itemId);
	}
	
	private void setItemForm(long id){
		rpc.findById((long)id, new AsyncCallback<Item>() {
			public void onSuccess(Item item) {
				// TODO: Mejorar, deberia setear el record directamente
				display.getForm().setValue("id", item.getId());
				display.getForm().setValue("name", item.getName());
			}

			public void onFailure(Throwable arg0) {
				// TODO: show message error
				System.out.println("Error en el fetch del item");
			}
		});
	}

	private void bind() {
		display.getSubmitButton().addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				final DynamicForm form = display.getForm();

				if ( form.validate() ) {
					System.out.println("Values Edited!");
					form.saveData();
					System.out.println("Form saved!");

					eventBus.fireEvent(new EditedItemEvent());

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
