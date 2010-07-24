package com.tda.presentation.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class RemoveItemEvent extends GwtEvent<RemoveItemEventHandler> {

	public static Type<RemoveItemEventHandler> TYPE = new Type<RemoveItemEventHandler>();
	private long id;
	
	public RemoveItemEvent(long id) {
		this.id = id;
	}
	
	public long getId(){
		return id;
	}

	@Override
	public Type<RemoveItemEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(RemoveItemEventHandler handler) {
		handler.onEvent(this);
	}
}
