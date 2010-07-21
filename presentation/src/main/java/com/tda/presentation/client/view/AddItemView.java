package com.tda.presentation.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.events.HasClickHandlers;
import com.tda.presentation.client.presenter.AddItemPresenter;

public class AddItemView extends Composite implements AddItemPresenter.Display {

	private final Window winModal;
	private final DynamicForm form;
	private final ButtonItem submitButton; 

	public AddItemView() {
		winModal = new Window();
        winModal.setWidth(360);
        winModal.setHeight(300);
        winModal.setTitle("Nuevo Item");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.centerInPage();

        form = new DynamicForm();
        form.setHeight100();
        form.setWidth100();
        form.setPadding(5);
        form.setLayoutAlign(VerticalAlignment.CENTER);

        form.setDataSource(ItemsDataSource.getInstance());
        form.setUseAllDataSourceFields(true);
        
        submitButton = new ButtonItem();  
        submitButton.setName("submit");  
        submitButton.setTitle("Submit");
        form.setFields(submitButton);

        winModal.addItem(form);

        initWidget(winModal);
	}

	public Widget asWidget() {
		return this;
	}

	public HasClickHandlers getSubmitButton() {
		return submitButton;
	}

	public void onDestroy() {
		winModal.destroy();
	}

	public Window getWindow() {
		return winModal;
	}

	public DynamicForm getForm() {
		return form;
	}
}
