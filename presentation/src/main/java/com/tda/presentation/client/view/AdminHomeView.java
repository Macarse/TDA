package com.tda.presentation.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.tda.presentation.client.presenter.AdminHomePresenter;

public class AdminHomeView extends Composite implements AdminHomePresenter.Display {

	@UiTemplate("AdminHomeView.ui.xml")
	interface AdminHomeViewUiBinder extends UiBinder<Widget, AdminHomeView> {
	}

	private static AdminHomeViewUiBinder uiBinder = GWT.create(AdminHomeViewUiBinder.class);

	@UiField
	VerticalPanel tabsContainer;

	@UiField
	VerticalPanel mainContainer;

	private TabSet topTabSet;
	
	public AdminHomeView() {
		initWidget(uiBinder.createAndBindUi(this));
		createTabs();
		tabsContainer.add(topTabSet);
	}

	private void createTabs() {
		topTabSet = new TabSet();  
        topTabSet.setTabBarPosition(Side.TOP);
        
		Tab userTab = new Tab("Usuarios");
		
		Tab itemsTab = new Tab("Items");

		topTabSet.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				System.out.println("clicked! " + topTabSet.getSelectedTab().getTitle());
				
			}
		});

		topTabSet.addTab(userTab);
		topTabSet.addTab(itemsTab);
	}

	public Widget asWidget() {
		return this;
	}

	public Panel getTabsContainer() {
		return tabsContainer;
	}

	public Panel getMainContainer() {
		return mainContainer;
	}
}
