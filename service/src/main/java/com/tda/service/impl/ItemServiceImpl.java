package com.tda.service.impl;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import com.tda.model.item.Item;
import com.tda.persistence.dao.ItemDAO;
import com.tda.service.api.ItemService;

public class ItemServiceImpl implements ItemService {

	private ItemDAO itemDAO;

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	@Transactional
	public void save(Item item) {
		itemDAO.save(item);
	}

	@Transactional
	@Secured("ROLE_ADMIN")
	public void delete(Item item) {
		itemDAO.delete(item);
	}

	@Transactional
	public void update(Item item) {
		itemDAO.update(item);
	}

	@Transactional(readOnly = true)
	public Item findById(Long id) {
		return itemDAO.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Item> findAll() {
		return itemDAO.findAll();
	}

	@Transactional(readOnly = true)
	public List<Item> findByName(String name) {
		return itemDAO.findByName(name);
	}

	@Transactional(readOnly = true)
	public List<Item> findByExample(Item example) {
		return itemDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<Item> findByDescription(String description) {
		return itemDAO.findByDescription(description);
	}

	@Transactional(readOnly = true)
	public List<Item> findByQuantityRange(Long minQ, Long maxQ) {
		return itemDAO.findByQuantityRange(minQ, maxQ);
	}
}
