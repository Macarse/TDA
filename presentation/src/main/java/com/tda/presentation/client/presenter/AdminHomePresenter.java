package com.tda.presentation.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.tda.model.item.Item;
import com.tda.presentation.client.view.CrudView;

public class AdminHomePresenter extends HomePresenter {
	private static final String ADMIN = "Admin";
	private HandlerManager eventBus;

	public AdminHomePresenter(HandlerManager eventBus, Display view) {
		super(eventBus, view);
	}

	@Override
	protected String getName() {
		return ADMIN;
	}

	@Override
	protected List<Presenter> getPresenters() {
		List<Presenter> presenters = new ArrayList<Presenter>();
		presenters.add(new ItemPresenter(eventBus, new CrudView<Item>()));

		return presenters;
	}

}
