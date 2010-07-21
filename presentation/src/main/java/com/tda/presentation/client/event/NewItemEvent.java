package com.tda.presentation.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.tda.model.Item;

public class NewItemEvent extends GwtEvent<NewItemEventHandler> {

	public static Type<NewItemEventHandler> TYPE = new Type<NewItemEventHandler>();
	private Item newItem;

	public NewItemEvent(Item newItem) {
		this.newItem = newItem;
	}

	public Item getNewItem() {
		return newItem;
	}

	@Override
	public Type<NewItemEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(NewItemEventHandler handler) {
		handler.onNewItem(this);
	}
}
