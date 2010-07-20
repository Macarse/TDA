package com.tda.presentation.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.tda.model.Item;

public interface ItemsView<T> {

	public interface Presenter<T> {
		void onAddButtonClicked();

		void onDeleteButtonClicked();
	}

	void setPresenter(Presenter<T> presenter);

	void setRowData(List<Item> items);

	Widget asWidget();
}
