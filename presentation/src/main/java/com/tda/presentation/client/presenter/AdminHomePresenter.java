package com.tda.presentation.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.tda.presentation.client.service.AdminServiceGWTWrapperAsync;

public class AdminHomePresenter implements Presenter {

	public interface Display {
		Widget asWidget();
		Panel getTabsContainer();
		Panel getMainContainer();
	}

	private Display display;
	private HandlerManager eventBus;
	private AdminServiceGWTWrapperAsync rpc;

	public AdminHomePresenter(AdminServiceGWTWrapperAsync rpc, HandlerManager eventBus, Display view) {
		this.display = view;
		this.rpc = rpc;
		this.eventBus = eventBus;
	}

	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
	}

	public void onDestroy() {
		/* Do nothing */
	}

}
