package com.tda.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.tda.model.Item;
import com.tda.persistence.ItemDAO;

public class ItemServiceImpl implements ItemService {
	@Autowired
	ItemDAO itemDAO;

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

}
