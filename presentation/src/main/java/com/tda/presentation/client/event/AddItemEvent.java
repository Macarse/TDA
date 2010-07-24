package com.tda.presentation.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddItemEvent extends GwtEvent<AddItemEventHandler> {

	public static Type<AddItemEventHandler> TYPE = new Type<AddItemEventHandler>();

	@Override
	public Type<AddItemEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AddItemEventHandler handler) {
		handler.onAddItem(this);
	}
}
