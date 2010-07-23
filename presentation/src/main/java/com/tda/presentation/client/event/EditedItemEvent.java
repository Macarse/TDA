package com.tda.presentation.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.tda.model.Item;

public class EditedItemEvent extends GwtEvent<EditedItemEventHandler> {

	public static Type<EditedItemEventHandler> TYPE = new Type<EditedItemEventHandler>();

	@Override
	public Type<EditedItemEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditedItemEventHandler handler) {
		handler.onEditedItem(this);
	}
}
