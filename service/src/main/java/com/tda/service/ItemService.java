package com.tda.service;

import java.util.List;
import com.tda.model.Item;

public interface ItemService {
	void save(Item item);

    void delete(Item item);

    void update(Item item);

    Item findById(Long id);

    List<Item> findAll();
    
    List<Item> findByName(String name);
    
    List<Item> findByExample(Item example);
    
    List<Item> findByDescription(String description);
    
    List<Item> findByRange(int minQ, int maxQ);
    
}
