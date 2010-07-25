package com.tda.presentation.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.smartgwt.client.widgets.Canvas;

public abstract interface Presenter {
	public abstract void attach(Canvas container);
	public abstract void go(final HasWidgets container);
	public abstract void onDestroy();
}
