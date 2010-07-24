package com.tda.presentation.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RemovedItemEvent extends GwtEvent<RemovedItemEventHandler> {

	public static Type<RemovedItemEventHandler> TYPE = new Type<RemovedItemEventHandler>();

	@Override
	public Type<RemovedItemEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RemovedItemEventHandler handler) {
		handler.onEvent(this);
	}
}
