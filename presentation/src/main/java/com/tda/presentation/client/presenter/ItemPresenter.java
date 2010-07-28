package com.tda.presentation.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.tda.model.item.Item;
import com.tda.presentation.client.datasource.CrudGwtRPCDS;
import com.tda.presentation.client.datasource.ItemGwtRPCDS;
import com.tda.presentation.client.service.CrudServiceGWTWrapperAsync;
import com.tda.presentation.client.service.ItemServiceGWTWrapper;

public class ItemPresenter extends CrudPresenter<Item> {

	private final CrudServiceGWTWrapperAsync<Item> rpc;
	private ItemGwtRPCDS dataSource;
	
	public ItemPresenter(HandlerManager eventBus, Display<Item> view) {
		super(eventBus, view);
		this.rpc = GWT.create(ItemServiceGWTWrapper.class);
		ItemGwtRPCDS.setRpc(this.rpc);
		try{
			this.dataSource = ItemGwtRPCDS.getInstance();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
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
