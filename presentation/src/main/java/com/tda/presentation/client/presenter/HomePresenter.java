package com.tda.presentation.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public abstract class HomePresenter implements Presenter {

	public interface Display {
		Widget asWidget();

		Panel getTabsContainer();

		Panel getMainContainer();

		DecoratedTabPanel getTab();
	}

	private Display display;
	private HandlerManager eventBus;
	private List<Presenter> tabsPresenters;

	public HomePresenter(HandlerManager eventBus, Display view) {
		this.display = view;
		this.eventBus = eventBus;
		tabsPresenters = new ArrayList<Presenter>();
	}

	public void go(HasWidgets container) {
		instancePresenters();
		appendPresenters();
		container.clear();
		display.getTab().selectTab(0);
		container.add(display.asWidget());
	}

	public void appendPresenters() {
		for (Presenter presenter : tabsPresenters) {
			presenter.go(display.getTab());
		}
	}

	public void instancePresenters() {
		tabsPresenters.addAll(getPresenters());
	}

	public void onDestroy() {
		/* Do nothing */
	}

	protected abstract List<Presenter> getPresenters();

	protected abstract String getName();

	public void go(DecoratedTabPanel panel) {
		panel.add(display.asWidget(), getName());
	}
}
