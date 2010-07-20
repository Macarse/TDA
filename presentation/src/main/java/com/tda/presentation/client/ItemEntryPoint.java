package com.tda.presentation.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.tda.model.Item;
import com.tda.presentation.client.service.ItemServiceGWTWrapper;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;
import com.tda.presentation.client.view.ItemsDataSource;
import com.tda.presentation.client.view.ItemsView;
import com.tda.presentation.client.view.ItemsViewImpl;

public class ItemEntryPoint implements EntryPoint {

	private ItemServiceGWTWrapperAsync rpc = GWT.create(ItemServiceGWTWrapper.class);

	public void onModuleLoad() {
		final Label label = new Label();
		RootPanel.get().add(label);

		final ItemsView<Item> view = new ItemsViewImpl<Item>();
		RootPanel.get().add(view.asWidget());

		/* Create one item */
		Item item = new Item();
		item.setName("Juan");
		rpc.save(item, new AsyncCallback<Void>() {
			
			public void onSuccess(Void arg0) {
				label.setText("Cool, added!");
				
			}
			
			public void onFailure(Throwable arg0) {
				label.setText("Ouch, failure! "+arg0.getMessage() );
			}
		});

		/* Get all items */
		rpc.findAll(new AsyncCallback<List<Item>>() {

			public void onFailure(Throwable arg0) {
				Window.alert("Could get any Items!");
				
			}

			public void onSuccess(List<Item> result) {
				view.setRowData(result);
			}

		});
	}
}
