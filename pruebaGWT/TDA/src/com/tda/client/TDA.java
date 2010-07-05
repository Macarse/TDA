package com.tda.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.tda.shared.Form;
import com.tda.shared.FormElement;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TDA implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */

	private final MedicFormServiceAsync medicService = GWT
	.create(MedicFormService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		final ListBox reply = new ListBox();

		medicService.getForm(new AsyncCallback<Form>() {

			@Override
			public void onFailure(Throwable caught) {
				reply.addItem("failure");
			}

			@Override
			public void onSuccess(Form result) {
				reply.addItem("success");
				for (FormElement formElement : result.getElements()) {
					RootPanel.get("nameFieldContainer").add(formElement.getWidget());
				}
			}
		});

		final Button sendButton = new Button("Send");

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		RootPanel.get("nameFieldContainer").add(reply);
		RootPanel.get("sendButtonContainer").add(sendButton);

	}
}
