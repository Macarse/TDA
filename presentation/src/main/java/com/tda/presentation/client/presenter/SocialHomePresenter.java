package com.tda.presentation.client.presenter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.HandlerManager;
import com.tda.model.patient.Patient;
import com.tda.presentation.client.view.CrudView;

public class SocialHomePresenter extends HomePresenter {
	private static final String SOCIAL = "Social";

	private HandlerManager eventBus;

	public SocialHomePresenter(HandlerManager eventBus, Display view) {
		super(eventBus, view);
		this.eventBus = eventBus;
	}

	@Override
	protected List<Presenter> getPresenters() {
		List<Presenter> presenters = new ArrayList<Presenter>();
		presenters.add(new PatientPresenter(eventBus, new CrudView<Patient>()));
		return presenters;
	}

	@Override
	protected String getName() {
		return SOCIAL;
	}

}
