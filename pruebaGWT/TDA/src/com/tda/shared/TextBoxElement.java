package com.tda.shared;

import java.io.Serializable;

import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("serial")
public class TextBoxElement implements FormElement, Serializable {

	
	@Override
	public Widget getWidget() {
		return new TextBox();
	}

}
