package com.tda.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tda.model.item.Item;
import com.tda.model.item.ItemBuilder;

public class ItemDAO extends GenericDAOImpl<Item> {
	@Override
	protected Class<Item> getDomainClass() {
		return Item.class;
	}

	public List<Item> findByNameContaining(String name) {
		Item exampleObject = ItemBuilder.createItem().withName(name).build();
		return this.findByExample(exampleObject);
	}

	public List<Item> findByDescriptionContaining(String description) {
		Item exampleObject = ItemBuilder.createItem()
				.withDescription(description).build();
		return this.findByExample(exampleObject);
	}

	@SuppressWarnings("unchecked")
	public List<Item> findByQuantityRange(Long minQ, Long maxQ) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Item.class);
		criteria.add(Restrictions.between("quantity", minQ, maxQ));
		return getHibernateTemplate().findByCriteria(criteria);
	}
}
