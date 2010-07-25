package com.tda.presentation.client.presenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.tab.Tab;
import com.tda.presentation.client.service.AdminServiceGWTWrapperAsync;
import com.tda.presentation.client.service.ItemServiceGWTWrapper;
import com.tda.presentation.client.service.ItemServiceGWTWrapperAsync;
import com.tda.presentation.client.view.ItemView;

public class AdminHomePresenter implements Presenter {

	public interface Display {
		Widget asWidget();
		Panel getTabsContainer();
		Panel getMainContainer();
		Tab getTab(int index);
		Canvas getTabCanvas(int index);
	}

	private Display display;
	private HandlerManager eventBus;
	private AdminServiceGWTWrapperAsync rpc;
	private List<Presenter> tabsPresenters;
	private ItemServiceGWTWrapperAsync itemRPC;

	public AdminHomePresenter(AdminServiceGWTWrapperAsync rpc, HandlerManager eventBus, Display view) {
		this.display = view;
		this.rpc = rpc;
		this.eventBus = eventBus;
		tabsPresenters = new ArrayList<Presenter>();
	}

	public void go(HasWidgets container) {
		instancePresenters();
		appendPresenters();
		bind();
		container.clear();
		container.add(display.asWidget());
	}
	
	public void appendPresenters(){
		int i =0;
		for (Presenter presenter : tabsPresenters) {
			Canvas c = display.getTabCanvas(i++);
			if (c == null){
				System.out.println("Error al obtener el canvas");
			}else
				presenter.attach(display.getTabCanvas(i++));
		}
	}
	
	public void instancePresenters(){
		tabsPresenters.add(new ItemPresenter(getItemRPC(), eventBus, new ItemView()));
	}
	
	public ItemServiceGWTWrapperAsync getItemRPC() {
		if ( itemRPC == null ) {
			itemRPC = GWT.create(ItemServiceGWTWrapper.class);
		}

		return itemRPC;
	}

	private void bind() {
	}

	public void onDestroy() {
		/* Do nothing */
	}

	public void attach(Canvas container) {
		bind();
		container.clear();
		container.addChild(display.asWidget());
	}

}
