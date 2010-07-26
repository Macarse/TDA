package com.tda.presentation.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.tda.model.item.Category;
import com.tda.model.item.Item;
import com.tda.model.item.MeasureUnit;
import com.tda.presentation.client.datasource.CrudGwtRPCDS;
import com.tda.presentation.client.datasource.ItemGwtRPCDS;
import com.tda.presentation.client.service.CrudServiceGWTWrapperAsync;

public class ItemPresenter extends CrudPresenter<Item> {

	private final Display<Item> display;

	public Display<Item> getDisplay() {
		return display;
	}

	private final CrudServiceGWTWrapperAsync<Item> rpc;
	private final HandlerManager eventBus;
	private Status status;
	private ItemGwtRPCDS dataSource;
	
	public ItemPresenter(HandlerManager eventBus, Display<Item> view) {
		super(eventBus, view);
		this.display = view;
		this.rpc = getService();
		this.dataSource = new ItemGwtRPCDS(this.rpc);
		this.eventBus = eventBus;
		this.status = Status.LIST;
		History.addValueChangeHandler(this);
	}

	private void loadFormWithRecord(Record record) {
		DynamicForm form = display.getForm();
		form.setValue("id", record.getAttribute("id"));
		form.setValue("name", record.getAttribute("name"));
		form.setValue("description", record.getAttribute("description"));
		form.setValue("measure", record.getAttribute("measure"));
		form.setValue("category", record.getAttribute("category"));
		form.setValue("quantity", record.getAttribute("quantity"));
	}

	public void onDestroy() {

	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			if (token.equals("itemsList")) {
				showList();
			} else if ( token.equals("addForm") ) {
				showForm();
			} else if ( token.equals("editForm") ) {
				showForm();
			}
		}
	}

	@Override
	protected CrudServiceGWTWrapperAsync<Item> getService() {
		return this.rpc;
	}

	@Override
	protected CrudGwtRPCDS<Item> getDataSource() {
		return this.dataSource;
	}

}
