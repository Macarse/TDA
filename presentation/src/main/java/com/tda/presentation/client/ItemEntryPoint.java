package com.tda.presentation.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class ItemEntryPoint implements EntryPoint {

	public void onModuleLoad() {
		AppController appViewer = new AppController();
		appViewer.go(RootPanel.get());
	}
}
