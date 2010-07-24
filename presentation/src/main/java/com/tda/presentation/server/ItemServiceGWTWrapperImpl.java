package com.tda.presentation.server;

import java.util.List;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.tda.model.item.Item;
import com.tda.presentation.client.service.ItemServiceGWTWrapper;
import com.tda.service.api.ItemService;

@SuppressWarnings("serial")
public class ItemServiceGWTWrapperImpl extends AutoinjectingRemoteServiceServlet implements
		ItemServiceGWTWrapper {

	private ItemService itemService;

	@Override
	protected void getBeansFromFactory(AutowireCapableBeanFactory beanFactory) {
		itemService = (ItemService) beanFactory.getBean("itemService");
	}

	public void save(Item item) {
		itemService.save(item);
		
	}

	public void delete(Item item) {
		itemService.delete(item);
		
	}

	public void update(Item item) {
		itemService.update(item);
		
	}

	public Item findById(Long id) {
		return itemService.findById(id);
	}

	public List<Item> findAll() {
		return itemService.findAll();
	}

	public List<Item> findByName(String name) {
		return itemService.findByName(name);
	}

	public List<Item> findByExample(Item example) {
		return itemService.findByExample(example);
	}

	public List<Item> findByDescription(String description) {
		return itemService.findByDescription(description);
	}

	public List<Item> findByQuantityRange(Long minQ, Long maxQ) {
		return itemService.findByQuantityRange(minQ, maxQ);
	}
}
