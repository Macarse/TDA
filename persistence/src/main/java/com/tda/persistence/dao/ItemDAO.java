package com.tda.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tda.model.item.Item;
import com.tda.model.paginator.Paginator;

public class ItemDAO extends GenericDAOImpl<Item> {
	@Override
	protected Class<Item> getDomainClass() {
		return Item.class;
	}

	@SuppressWarnings("unchecked")
	public List<Item> findByNameContaining(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Item.class);
		criteria.add(Restrictions.like("name", "%" + name + "%"));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<Item> findByDescriptionContaining(String description) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Item.class);
		criteria.add(Restrictions.like("description", "%" + description + "%"));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<Item> findByQuantityRange(Long minQ, Long maxQ) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Item.class);
		criteria.add(Restrictions.between("quantity", minQ, maxQ));
		return getHibernateTemplate().findByCriteria(criteria);
	}
}
