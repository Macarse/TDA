package com.tda.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tda.shared.Form;

@RemoteServiceRelativePath("medic_form")
public interface MedicFormService extends RemoteService {

	public Form getForm();
}
