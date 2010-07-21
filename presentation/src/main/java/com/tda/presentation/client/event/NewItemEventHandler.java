package com.tda.presentation.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface NewItemEventHandler extends EventHandler {
	void onNewItem(NewItemEvent event);
}
