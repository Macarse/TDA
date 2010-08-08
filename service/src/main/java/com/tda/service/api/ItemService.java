package com.tda.service.api;

import java.util.List;

import com.tda.model.item.Category;
import com.tda.model.item.Item;
import com.tda.model.item.MeasureUnit;
import com.tda.persistence.paginator.Paginator;

public interface ItemService {

	void save(Item item);

	void delete(Item item);

	void update(Item item);

	Item findById(Long id);

	List<Item> findAll();
	
	List<Item> findAllPaged(Paginator paginator);

	List<Item> findByName(String name);

	List<Item> findByExample(Item example);

	List<Item> findByDescription(String description);

	List<Item> findByQuantityRange(Long minQ, Long maxQ);

	List<Item> findByNameContaining(String name);

	List<Item> findByDescriptionContaining(String description);

	List<Item> findByCategory(Category category);

	List<Item> findByMeasureUnit(MeasureUnit measureUnit);

}
