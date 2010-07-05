package com.tda.shared;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Form implements Serializable {

	ArrayList<FormElement> elements;

	public Form() {
		this.elements = new ArrayList<FormElement>();
	}

	public void addElement(FormElement e) {
		this.elements.add(e);
	}

	public ArrayList<FormElement> getElements() {
		return elements;
	}

	public void setElements(ArrayList<FormElement> elements) {
		this.elements = elements;
	}
}
