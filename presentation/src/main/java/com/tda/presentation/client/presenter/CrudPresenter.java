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

public abstract class CrudPresenter<T> implements Presenter,
		ValueChangeHandler<String> {

	private static final String ID = "id";
	private static final String LIST = "List";
	private static final String EDIT_FORM = "EditForm";
	private static final String ADD_FORM = "AddForm";

	public interface Display<T> {
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

		void setDataSource(CrudGwtRPCDS<T> ds);
	}

	private final Display<T> display;

	public Display<T> getDisplay() {
		return display;
	}

	private final HandlerManager eventBus;
	private Status status;

	public CrudPresenter(HandlerManager eventBus, Display<T> view) {
		this.display = view;
		this.eventBus = eventBus;
		this.status = Status.LIST;
		History.addValueChangeHandler(this);
	}

	protected abstract CrudServiceGWTWrapperAsync<T> getService();

	protected abstract CrudGwtRPCDS<T> getDataSource();

	public void go(HasWidgets container) {
		display.setDataSource(getDataSource());
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	public void go(DecoratedTabPanel panel) {
		display.setDataSource(getDataSource());
		bind();
		panel.add(display.asWidget(), getTitle());
	}

	protected void showForm() {
		display.getListContainer().setVisible(false);
		display.getFormContainer().setVisible(true);
	}

	protected void showList() {
		display.getFormContainer().setVisible(false);
		display.getListContainer().setVisible(true);
		display.getGrid().fetchData();
		display.getForm().clearValues();
	}

	private void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				status = Status.ADD;
				History.newItem(getPrefix() + ADD_FORM);
			}
		});

		display.getEditButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				/* No item selected */
				if (display.getClickedRow() == null)
					return;

				getDataSource().copyValues(display.getClickedRow(),
						display.getForm());
				status = Status.EDIT;
				History.newItem(getPrefix() + EDIT_FORM);
			}

		});

		display.getSubmitButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				if (!display.getForm().validate()) {
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
				if (display.getClickedRow() == null)
					return;

				final long id = Long.valueOf(display.getClickedRow()
						.getAttribute(ID));

				SC.ask("¿Desea borrar el elemento seleccionado?",
						new BooleanCallback() {
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
		T item = getDataSource().get(display.getForm());
		getService().update(item, new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				SC.say("El elemento no pudo ser editado. "
						+ caught.getMessage());
			}

			public void onSuccess(Void result) {
				History.newItem(getPrefix() + LIST);
				SC.say("Elemento editado.");
			}
		});
	}

	private void addItem() {
		T item = getDataSource().get(display.getForm());
		getService().save(item, new AsyncCallback<Void>() {

			public void onFailure(Throwable caught) {
				SC.say("El elemento no pudo ser agregado. "
						+ caught.getMessage());

			}

			public void onSuccess(Void result) {
				History.newItem(getPrefix() + LIST);
				SC.say("Elemento agregado.");
			}
		});

	}

	protected abstract String getTitle();

	protected abstract String getPrefix();

	/*
	 * TODO: Improve!
	 */
	private void removeItem(long id) {

		/* First got to fetch item(id) */
		getService().findById(id, new AsyncCallback<T>() {
			public void onFailure(Throwable arg0) {
				SC.say("Elemento no encontrado");
			}

			public void onSuccess(T item) {
				getService().delete(item, new AsyncCallback<Void>() {

					public void onFailure(Throwable arg0) {
						SC.say("Error al eliminar el elemento");
					}

					public void onSuccess(Void arg0) {
						SC.say("Elemento removido");

						display.getGrid().fetchData();
						display.getGrid().redraw();
					}

				});
			}
		});
	}

	protected String getListToken() {
		return getPrefix() + LIST;
	}

	protected String getAddFormToken() {
		return getPrefix() + ADD_FORM;
	}

	protected String getEditFormToken() {
		return getPrefix() + EDIT_FORM;
	}

	public abstract void onDestroy();

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			if (token.equals(getListToken())) {
				showList();
			} else if (token.equals(getAddFormToken())) {
				showForm();
			} else if (token.equals(getEditFormToken())) {
				showForm();
			}
		}
	}
}
