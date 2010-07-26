package com.tda.presentation.client.datasource;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.tda.model.item.Item;
import com.tda.presentation.client.service.CrudServiceGWTWrapperAsync;
import com.tda.presentation.client.service.ItemServiceGWTWrapper;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;

public class ItemDataSource extends CrudGwtRPCDS<Item> {

	private ItemServiceGWTWrapperAsync rpc = GWT.create(ItemServiceGWTWrapper.class);
	
	@Override
	protected CrudServiceGWTWrapperAsync<Item> getRpc() {
		return rpc;
	}

	@Override
	public void copyValues(DynamicForm form, Record record) {
		// TODO Auto-generated method stub

	}

	@Override
	public void copyValues(Record record, DynamicForm form) {
		// TODO Auto-generated method stub

	}

	@Override
	public Item get(DynamicForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void copyValues(Item from, ListGridRecord to) {
		// TODO Auto-generated method stub
		
	}

}
