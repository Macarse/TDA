package com.tda.presentation.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.smartgwt.client.widgets.Canvas;
import com.tda.presentation.client.presenter.AdminHomePresenter;
import com.tda.presentation.client.presenter.ItemPresenter;
import com.tda.presentation.client.presenter.LoginPresenter;
import com.tda.presentation.client.presenter.Presenter;
import com.tda.presentation.client.service.AdminServiceGWTWrapper;
import com.tda.presentation.client.service.AdminServiceGWTWrapperAsync;
import com.tda.presentation.client.service.ItemServiceGWTWrapper;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;
import com.tda.presentation.client.service.LoginServiceGWTWrapper;
import com.tda.presentation.client.service.LoginServiceGWTWrapperAsync;
import com.tda.presentation.client.view.AdminHomeView;
import com.tda.presentation.client.view.ItemView;
import com.tda.presentation.client.view.LoginView;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private ItemServiceGWTWrapperAsync itemRPC;
	private LoginServiceGWTWrapperAsync loginRPC;
	private AdminServiceGWTWrapperAsync adminRPC;

	private HasWidgets container;
	private Presenter presenter;

	public AppController() {
		this.eventBus = new HandlerManager(null);
		bind();
	}

	public AdminServiceGWTWrapperAsync getAdminRPC() {
		if ( adminRPC == null ) {
			adminRPC = GWT.create(AdminServiceGWTWrapper.class);
		}

		return adminRPC;
	}

	public ItemServiceGWTWrapperAsync getItemRPC() {
		if ( itemRPC == null ) {
			itemRPC = GWT.create(ItemServiceGWTWrapper.class);
		}

		return itemRPC;
	}

	public LoginServiceGWTWrapperAsync getLoginRPC() {
		if ( loginRPC == null ) {
			loginRPC = GWT.create(LoginServiceGWTWrapper.class);
		}

		return loginRPC;
	}

	private void bind() {
		History.addValueChangeHandler(this);
		
	}

	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("login");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();

		if (token != null) {
			if (presenter != null) {
				presenter.onDestroy();
				presenter = null;
			}

			if (token.equals("itemList")) {
				presenter = new ItemPresenter(getItemRPC(), eventBus, new ItemView());
			} else if ( token.equals("login") ) {
				presenter = new LoginPresenter(getLoginRPC(), eventBus, new LoginView());
			} else if ( token.equals("adminHome") ) {
				presenter = new AdminHomePresenter(getAdminRPC(), eventBus, new AdminHomeView());
			}
			
			if (presenter != null) {
				presenter.go(container);
			}
		}
	}

	public void onDestroy() {
		/* Do nothing */
	}

	public void attach(Canvas container) {
		// TODO Auto-generated method stub
		
	}
}
