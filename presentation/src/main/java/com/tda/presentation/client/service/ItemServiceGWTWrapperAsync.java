package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.tda.model.item.Item;

public interface ItemServiceGWTWrapperAsync {

	void delete(Item item, AsyncCallback<Void> callback);

	void findAll(AsyncCallback<List<Item>> callback);

	void findByDescription(String description,
			AsyncCallback<List<Item>> callback);

	void findByExample(Item example, AsyncCallback<List<Item>> callback);

	void findById(Long id, AsyncCallback<Item> callback);

	void findByName(String name, AsyncCallback<List<Item>> callback);

	void findByQuantityRange(Long minQ, Long maxQ,
			AsyncCallback<List<Item>> callback);

	void save(Item item, AsyncCallback<Void> callback);

	void update(Item item, AsyncCallback<Void> callback);

}
