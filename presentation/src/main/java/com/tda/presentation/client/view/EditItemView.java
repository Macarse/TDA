package com.tda.presentation.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.events.HasClickHandlers;
import com.tda.presentation.client.datasource.ItemGwtRPCDS;
import com.tda.presentation.client.presenter.EditItemPresenter;

public class EditItemView extends Composite implements EditItemPresenter.Display {

	private final Window winModal;
	private final DynamicForm form;
	private final ButtonItem submitButton; 

	public EditItemView() {
		winModal = new Window();
        winModal.setWidth(360);
        winModal.setHeight(300);
        winModal.setTitle("Editar Item");
        winModal.setShowMinimizeButton(false);
        winModal.setIsModal(true);
        winModal.setShowModalMask(true);
        winModal.centerInPage();

        form = new DynamicForm();
        form.setHeight100();
        form.setWidth100();
        form.setPadding(5);
        form.setLayoutAlign(VerticalAlignment.CENTER);
        
        form.setDataSource(ItemGwtRPCDS.getInstance());
        form.setUseAllDataSourceFields(true);
        form.setSaveOperationType(DSOperationType.UPDATE);
        
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
