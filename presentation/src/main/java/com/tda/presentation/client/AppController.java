package com.tda.presentation.client;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.tda.model.applicationuser.ApplicationUserGWT;
import com.tda.model.applicationuser.Authority;
import com.tda.presentation.client.presenter.AdminHomePresenter;
import com.tda.presentation.client.presenter.LoginPresenter;
import com.tda.presentation.client.presenter.Presenter;
import com.tda.presentation.client.presenter.SocialHomePresenter;
import com.tda.presentation.client.service.ApplicationUserServiceGWTWrapper;
import com.tda.presentation.client.service.ApplicationUserServiceGWTWrapperAsync;
import com.tda.presentation.client.service.ItemServiceGWTWrapper;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;
import com.tda.presentation.client.service.LoginServiceGWTWrapper;
import com.tda.presentation.client.service.LoginServiceGWTWrapperAsync;
import com.tda.presentation.client.view.HomeView;
import com.tda.presentation.client.view.LoginView;

public class AppController implements Presenter, ValueChangeHandler<String> {

	public static final String ADMIN_HOME = "adminHome";
	public static final String SOCIAL_HOME = "socialHome";
	private ApplicationUserGWT loggedApplicationUser;
	public static final String LOGIN = "login";

	private static final HashMap<String, String> redirectMap;

	static {
		redirectMap = new HashMap<String, String>(2);
		redirectMap.put("ROLE_ADMIN", AppController.ADMIN_HOME);
		redirectMap.put("ROLE_SOCIAL_WORKER", AppController.SOCIAL_HOME);
	}

	private final HandlerManager eventBus;
	private ItemServiceGWTWrapperAsync itemRPC;
	private LoginServiceGWTWrapperAsync loginRPC;
	private ApplicationUserServiceGWTWrapperAsync applicationUserRPC;

	private HasWidgets container;
	private Presenter presenter;
	private static AppController instance;

	public static AppController getInstance() {
		if (instance == null) {
			instance = new AppController();
		}
		return instance;
	}

	private AppController() {
		this.eventBus = new HandlerManager(null);
		bind();
	}

	public ApplicationUserServiceGWTWrapperAsync getapplicationUserRPC() {
		if (applicationUserRPC == null) {
			applicationUserRPC = GWT
					.create(ApplicationUserServiceGWTWrapper.class);
		}

		return applicationUserRPC;
	}

	public ItemServiceGWTWrapperAsync getItemRPC() {
		if (itemRPC == null) {
			itemRPC = GWT.create(ItemServiceGWTWrapper.class);
		}

		return itemRPC;
	}

	public LoginServiceGWTWrapperAsync getLoginRPC() {
		if (loginRPC == null) {
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
			History.newItem(LOGIN);
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

			if (token.equals(LOGIN)) {
				presenter = new LoginPresenter(getLoginRPC(), eventBus,
						new LoginView());
			} else if (token.equals(ADMIN_HOME)) {
				presenter = new AdminHomePresenter(eventBus, new HomeView());
			} else if (token.equals(SOCIAL_HOME)) {
				presenter = new SocialHomePresenter(eventBus, new HomeView());
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
	}

	public void go(DecoratedTabPanel panel) {
	}

	public void redirect(String username) {

		getapplicationUserRPC().findByUsername(username,
				new AsyncCallback<ApplicationUserGWT>() {

					public void onSuccess(ApplicationUserGWT result) {
						/* TODO: Fix This. */
						loggedApplicationUser = result;
						Authority auth = (Authority) loggedApplicationUser
								.getMyAuthorities().toArray()[0];
						History.newItem(redirectMap.get(auth.getAuthority()));
					}

					public void onFailure(Throwable caught) {
						SC.say("Usuario no encontrado. Error en la base de datos.");
					}
				});
	}
}
