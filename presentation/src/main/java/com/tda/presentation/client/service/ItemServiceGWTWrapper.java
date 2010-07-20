package com.tda.presentation.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.tda.model.Item;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("item")
public interface ItemServiceGWTWrapper extends RemoteService {

	void save(Item item);

    void delete(Item item);

    void update(Item item);

    Item findById(Long id);

    List<Item> findAll();
    
    List<Item> findByName(String name);
    
    List<Item> findByExample(Item example);
    
    List<Item> findByDescription(String description);
    
    List<Item> findByQuantityRange(Long minQ, Long maxQ);

}
