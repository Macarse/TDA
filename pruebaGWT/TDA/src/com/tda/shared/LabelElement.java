package com.tda.shared;

import java.io.Serializable;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("serial")
public class LabelElement implements FormElement, Serializable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Widget getWidget() {
		return new Label(this.name);
	}

}
