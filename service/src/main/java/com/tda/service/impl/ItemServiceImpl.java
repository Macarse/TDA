package com.tda.service.impl;

import java.util.List;

import com.tda.model.item.Item;
import com.tda.persistence.dao.ItemDAO;
import com.tda.service.api.ItemService;

public class ItemServiceImpl implements ItemService {

	ItemDAO itemDAO;

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public void save(Item item) {
		itemDAO.save(item);
	}

	public void delete(Item item) {
		itemDAO.delete(item);
	}

	public void update(Item item) {
		itemDAO.update(item);
	}

	public Item findById(Long id) {
		return itemDAO.findById(id);
	}

	public List<Item> findAll() {
		return itemDAO.findAll();
	}

	public List<Item> findByName(String name) {
		return itemDAO.findByName(name);
	}
	
	public List<Item> findByExample(Item example) {
		return itemDAO.findByExample(example);
	}

	public List<Item> findByDescription(String description) {
		return itemDAO.findByDescription(description);
	}

	public List<Item> findByQuantityRange(Long minQ, Long maxQ) {
		return itemDAO.findByQuantityRange(minQ, maxQ);
	}
}
