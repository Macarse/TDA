package com.tda.presentation.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.tda.presentation.client.presenter.HomePresenter;

public class HomeView extends Composite implements HomePresenter.Display {

	@UiTemplate("HomeView.ui.xml")
	interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
	}

	private static HomeViewUiBinder uiBinder = GWT
			.create(HomeViewUiBinder.class);

	@UiField
	VerticalPanel tabsContainer;

	@UiField
	VerticalPanel mainContainer;

	private DecoratedTabPanel tabSet;

	public HomeView() {
		initWidget(uiBinder.createAndBindUi(this));
		createTabs();
		tabsContainer.add(tabSet);
	}

	private void createTabs() {
		tabSet = new DecoratedTabPanel();
		tabSet.setWidth("500");
		tabSet.setHeight("500");
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

	public DecoratedTabPanel getTab(int index) {
		return tabSet;
	}

	public DecoratedTabPanel getTab() {
		return tabSet;
	}

}
