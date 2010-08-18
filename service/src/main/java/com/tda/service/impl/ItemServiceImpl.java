package com.tda.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tda.model.item.Category;
import com.tda.model.item.Item;
import com.tda.model.item.ItemBuilder;
import com.tda.model.item.MeasureUnit;
import com.tda.persistence.dao.ItemDAO;
import com.tda.persistence.paginator.Paginator;
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
	public List<Item> findByNameContaining(String name) {
		return itemDAO.findByNameContaining(name);
	}

	@Transactional(readOnly = true)
	public List<Item> findByExample(Item example) {
		return itemDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<Item> findByDescriptionContaining(String description) {
		return itemDAO.findByDescriptionContaining(description);
	}

	@Transactional(readOnly = true)
	public List<Item> findByQuantityRange(Long minQ, Long maxQ) {
		return itemDAO.findByQuantityRange(minQ, maxQ);
	}

	public List<Item> findByName(String name) {
		Item example = ItemBuilder.createItem().withName(name).build();
		return itemDAO.findByExample(example);
	}

	public List<Item> findByDescription(String description) {
		Item example = ItemBuilder.createItem().withDescription(description)
				.build();
		return itemDAO.findByExample(example);
	}

	public List<Item> findByCategory(Category category) {
		Item example = ItemBuilder.createItem().withCategory(category).build();
		return itemDAO.findByExample(example);
	}

	public List<Item> findByMeasureUnit(MeasureUnit measureUnit) {
		Item example = ItemBuilder.createItem().withMeasureUnit(measureUnit)
				.build();
		return itemDAO.findByExample(example);
	}

	public List<Item> findAllPaged(Paginator paginator) {
		return itemDAO.findAllPaged(paginator);
	}

	public List<Item> findByExamplePaged(Item example, Paginator paginator) {
		return itemDAO.findByExamplePaged(example, paginator);
	}
}
