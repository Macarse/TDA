package com.tda.presentation.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.tda.presentation.client.presenter.LoginPresenter;

public class LoginView extends Composite implements LoginPresenter.Display {

	@UiTemplate("LoginView.ui.xml")
	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
	}

	private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

	@UiField
	Panel formContainer;

	private DynamicForm form;
	private Button loginButton;

	public LoginView() {
		initWidget(uiBinder.createAndBindUi(this));

		createForm();

		formContainer.add(form);
		formContainer.add(loginButton);
	}

	private void createForm() {
		form = new DynamicForm();
		TextItem login = new TextItem("user", "Usuario");
		PasswordItem password = new PasswordItem("passwd", "Contraseña");
		form.setFields(login, password);

		loginButton = new Button("Login");
	}

	public HasClickHandlers getLoginButton() {
		return loginButton;
	}

	public DynamicForm getForm() {
		return form;
	}

	public Widget asWidget() {
		return this;
	}

}
