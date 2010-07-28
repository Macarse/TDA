package com.tda.presentation.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.tda.presentation.client.AppController;
import com.tda.presentation.client.service.LoginServiceGWTWrapperAsync;

public class LoginPresenter implements Presenter {

	public static final String PASSWORD = "passwd";
	public static final String USERNAME = "user";

	public interface Display {
		HasClickHandlers getLoginButton();

		DynamicForm getForm();

		Widget asWidget();
	}

	private Display display;
	private HandlerManager eventBus;
	private LoginServiceGWTWrapperAsync rpc;
	private LoginClickHandler loginClickHandler;

	public LoginPresenter(LoginServiceGWTWrapperAsync rpc,
			HandlerManager eventBus, Display view) {
		this.display = view;
		this.rpc = rpc;
		this.eventBus = eventBus;
		this.loginClickHandler = new LoginClickHandler();
	}

	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	private void bind() {
		display.getLoginButton().addClickHandler(loginClickHandler);
		EnterToLogin enterToLogin = new EnterToLogin();

		display.getForm().getField(USERNAME).addKeyUpHandler(enterToLogin);
		display.getForm().getField(PASSWORD).addKeyUpHandler(enterToLogin);

	}

	private class EnterToLogin implements KeyUpHandler {

		public void onKeyUp(KeyUpEvent event) {
			if ("Enter".equals(event.getKeyName())) {
				loginClickHandler.onClick(null);
			}
		}

	}

	final class LoginClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			DynamicForm form = display.getForm();
			if (!form.validate())
				return;

			final String user = form.getValueAsString(USERNAME);
			final String passwd = form.getValueAsString(PASSWORD);
			rpc.login(user, passwd, new AsyncCallback<Boolean>() {

				public void onSuccess(Boolean result) {
					if (result) {
						AppController.getInstance().redirect(user);
					} else {
						display.getForm().setValue(PASSWORD, "");
						SC.say("Usuario o contraseña inválidos.");
					}

				}

				public void onFailure(Throwable caught) {
					SC.say("No se pudo conectar con la base de datos. "
							+ caught.getLocalizedMessage());
				}
			});
		}
	}

	public void onDestroy() {
		/* Do nothing */
	}

	public void attach(Canvas container) {
		bind();
		container.clear();
		container.addChild(display.asWidget());
	}

	public void go(DecoratedTabPanel panel) {
	}

}
