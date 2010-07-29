package com.tda.presentation.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.tda.model.applicationuser.ApplicationUserGWT;
import com.tda.presentation.client.datasource.ApplicationUserGwtRPCDS;
import com.tda.presentation.client.datasource.CrudGwtRPCDS;
import com.tda.presentation.client.service.ApplicationUserServiceGWTWrapper;
import com.tda.presentation.client.service.CrudServiceGWTWrapperAsync;

public class ApplicationUserPresenter extends CrudPresenter<ApplicationUserGWT> {

	private static final String PREFIX = "applicationUser";
	private final CrudServiceGWTWrapperAsync<ApplicationUserGWT> rpc;
	private ApplicationUserGwtRPCDS dataSource;

	public ApplicationUserPresenter(HandlerManager eventBus,
			Display<ApplicationUserGWT> view) {
		super(eventBus, view);
		this.rpc = GWT.create(ApplicationUserServiceGWTWrapper.class);
		ApplicationUserGwtRPCDS.setRpc(this.rpc);
		try {
			this.dataSource = ApplicationUserGwtRPCDS.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void onDestroy() {
	}

	@Override
	protected CrudServiceGWTWrapperAsync<ApplicationUserGWT> getService() {
		return this.rpc;
	}

	@Override
	protected CrudGwtRPCDS<ApplicationUserGWT> getDataSource() {
		return this.dataSource;
	}

	@Override
	protected String getPrefix() {
		return PREFIX;
	}

}
