package com.tda.service;

import java.util.List;
import com.tda.model.Item;

public interface ItemService {
	void save(Item item);

    void delete(Item item);

    void update(Item item);

    Item findById(Long id);

    List<Item> findAll();
}
