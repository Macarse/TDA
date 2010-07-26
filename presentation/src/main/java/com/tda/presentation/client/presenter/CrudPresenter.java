package com.tda.presentation.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.tda.presentation.client.datasource.CrudGwtRPCDS;
import com.tda.presentation.client.service.CrudServiceGWTWrapperAsync;

public abstract class CrudPresenter<T> implements Presenter, ValueChangeHandler<String> {

	public interface Display {
		HasClickHandlers getAddButton();
		HasClickHandlers getEditButton();
		HasClickHandlers getDeleteButton();
		HasClickHandlers getSubmitButton();
		ListGrid getGrid();
		Record getClickedRow();
		Record[] getSelectedRows();
		DynamicForm getForm();
		Widget asWidget();
		Panel getListContainer();
		Panel getFormContainer();
		Panel getParentContainer();
	}


	private final Display display;

	public Display getDisplay() {
		return display;
	}

	/*
	 * TODO: ItemServiceGWTWrapperAsync must be generic
	 * 		 as well ItemGwtRPCDS
	 */           
	private final HandlerManager eventBus;
	private Status status;
	
	/*
	 * TODO: ItemServiceGWTWrapperAsync must be generic
	 * 		 as well ItemGwtRPCDS
	 */           
	public CrudPresenter(HandlerManager eventBus, Display view) {
		this.display = view;
		this.eventBus = eventBus;
		this.status = Status.LIST;
		History.addValueChangeHandler(this);
	}
	
	protected abstract CrudServiceGWTWrapperAsync<T> getService();
	
	protected abstract CrudGwtRPCDS<T> getDataSource();

	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}
	
	public void go(DecoratedTabPanel panel) {
		bind();
		panel.add(display.asWidget(), "items");
	}

	private void showForm() {
		display.getListContainer().setVisible(false);
		display.getFormContainer().setVisible(true);
	}

	private void showList() {
		display.getFormContainer().setVisible(false);
		display.getListContainer().setVisible(true);
		display.getGrid().fetchData();
		display.getForm().clearValues();
	}

	private void bind() {		
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				status = Status.ADD;
				History.newItem("addForm");
			}
		});
		
		display.getEditButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				/* No item selected */
				if ( display.getClickedRow() == null )
					return;

				/*
				 * TODO: ItemServiceGWTWrapperAsync must be generic
				 * 		 as well ItemGwtRPCDS
				 */           
				getDataSource().copyValues(display.getClickedRow(), display.getForm());
				status = Status.EDIT;
				History.newItem("editForm");
			}


		});

		display.getSubmitButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				if ( !display.getForm().validate() ) {
					return;
				}

				switch (status) {
				case ADD:
					addItem();
					break;

				case EDIT:
					editItem();
					break;

				default:
					break;
				}
			}
		});

		display.getDeleteButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				/* No item selected */
				if ( display.getClickedRow() == null )
					return;

				final long id = Long.valueOf(display.getClickedRow().getAttribute("id"));

				SC.ask("Desea borrar el item?", new BooleanCallback() {
					public void execute(Boolean value) {
						if (value) {
							removeItem(id);
						}
					}
				});
			}
		});
	}

	private void editItem() {
		/*
		 * TODO: ItemServiceGWTWrapperAsync must be generic
		 * 		 as well ItemGwtRPCDS, Item
		 */           
		T item = getDataSource().get(display.getForm());
		getService().update(item, new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				SC.say("Item no pudo ser editado. " + caught.getMessage());
			}

			public void onSuccess(Void result) {
				History.newItem("itemsList");
				SC.say("Item editado.");
			}
		});
	}

	private void addItem() {
		/*
		 * TODO: ItemServiceGWTWrapperAsync must be generic
		 * 		 as well ItemGwtRPCDS, Item
		 */           
		T item = getDataSource().get(display.getForm());
		getService().save(item, new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				SC.say("Item no pudo ser agregado. " + caught.getMessage());
				
			}

			public void onSuccess(Void result) {
				History.newItem("itemsList");
				SC.say("Item agregado.");
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
		getService().findById(id, new AsyncCallback<T>() {
			public void onFailure(Throwable arg0) {
				SC.say("Error al retribuir el item");
			}

			public void onSuccess(T item) {
				getService().delete(item, new AsyncCallback<Void>(){

					public void onFailure(Throwable arg0) {
						SC.say("Error al eliminar el item");
					}

					public void onSuccess(Void arg0) {
						SC.say("Item removido");
						/* TODO: This is not updating data */
						display.getGrid().fetchData();
						display.getGrid().redraw();
					}
					
				});
			}
		});
	}

	public abstract void onDestroy();
	
	public abstract void onValueChange(ValueChangeEvent<String> event);
}
