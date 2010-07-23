package com.tda.presentation.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NewItemEvent extends GwtEvent<NewItemEventHandler> {

	public static Type<NewItemEventHandler> TYPE = new Type<NewItemEventHandler>();

	@Override
	public Type<NewItemEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(NewItemEventHandler handler) {
		handler.onNewItem(this);
	}
}
