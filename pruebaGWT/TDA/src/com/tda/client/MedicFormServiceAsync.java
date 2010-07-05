package com.tda.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tda.shared.Form;

public interface MedicFormServiceAsync {

	void getForm(AsyncCallback<Form> callback);

}
