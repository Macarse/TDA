package com.tda.presentation.client.event;

import com.google.gwt.event.shared.GwtEvent;

/*
 * TODO: Ver si se puede hacer con generics
 */
public class EditItemEvent extends GwtEvent<EditItemEventHandler> {

	public static Type<EditItemEventHandler> TYPE = new Type<EditItemEventHandler>();
	private long id;
	
	public long getId() {
		return id;
	}

	public EditItemEvent(long id) {
		this.id = id;
	}
	
	@Override
	public Type<EditItemEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditItemEventHandler handler) {
		handler.onEditItem(this);
	}
}
