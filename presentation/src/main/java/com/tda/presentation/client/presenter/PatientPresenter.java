package com.tda.presentation.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.tda.model.patient.Patient;
import com.tda.presentation.client.datasource.CrudGwtRPCDS;
import com.tda.presentation.client.datasource.PatientGwtRPCDS;
import com.tda.presentation.client.service.CrudServiceGWTWrapperAsync;
import com.tda.presentation.client.service.PatientServiceGWTWrapper;

public class PatientPresenter extends CrudPresenter<Patient> {

	private static final String PREFIX = "patient";
	private final CrudServiceGWTWrapperAsync<Patient> rpc;
	private PatientGwtRPCDS dataSource;

	public PatientPresenter(HandlerManager eventBus, Display<Patient> view) {
		super(eventBus, view);
		this.rpc = GWT.create(PatientServiceGWTWrapper.class);
		PatientGwtRPCDS.setRpc(this.rpc);
		try {
			this.dataSource = PatientGwtRPCDS.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void onDestroy() {
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			if (token.equals(getListToken())) {
				showList();
			} else if (token.equals(getAddFormToken())) {
				showForm();
			} else if (token.equals(getEditFormToken())) {
				showForm();
			}
		}
	}

	@Override
	protected CrudServiceGWTWrapperAsync<Patient> getService() {
		return this.rpc;
	}

	@Override
	protected CrudGwtRPCDS<Patient> getDataSource() {
		return this.dataSource;
	}

	@Override
	protected String getPrefix() {
		return PREFIX;
	}

}
