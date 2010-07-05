package com.tda.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.tda.client.MedicFormService;
import com.tda.shared.Form;
import com.tda.shared.LabelElement;
import com.tda.shared.TextBoxElement;

@SuppressWarnings("serial")
public class MedicFormServiceImpl extends RemoteServiceServlet implements
		MedicFormService {

	@Override
	public Form getForm() {
		Form ret = new Form();
		LabelElement label;
		TextBoxElement tb;

		label = new LabelElement();
		label.setName("Campo1");
		ret.addElement(label);

		tb = new TextBoxElement();
		ret.addElement(tb);
				
		label = new LabelElement();
		label.setName("Campo2");
		ret.addElement(label);

		tb = new TextBoxElement();
		ret.addElement(tb);

		return ret;
	}

	

}
