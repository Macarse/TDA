package com.tda.presentation.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import com.tda.presentation.client.service.ItemServiceGWTWrapper;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;

public class ItemEntryPoint implements EntryPoint {

	public void onModuleLoad() {
		ItemServiceGWTWrapperAsync rpc = GWT.create(ItemServiceGWTWrapper.class);
		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(rpc, eventBus);
		appViewer.go(RootPanel.get());
	}
}
