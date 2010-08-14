package com.tda.persistence.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tda.persistence.paginator.Order;
import com.tda.persistence.paginator.Paginator;

public abstract class GenericDAOImpl<T> extends HibernateDaoSupport implements
		GenericDAO<T> {
	protected Class<T> persistentClass = getDomainClass();

	protected abstract Class<T> getDomainClass();

	public void save(final T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	public void delete(final T entity) {
		getHibernateTemplate().delete(entity);
	}

	public void update(final T entity) {
		getHibernateTemplate().update(entity);
	}

	public T findById(final Long id) {
		return (T) getHibernateTemplate().get(this.persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getHibernateTemplate().find(
				"from " + this.persistentClass.getName() + " o");
	}

	public void deleteById(final Long id) {
		T obj = this.findById(id);
		getHibernateTemplate().delete(obj);
	}

	@SuppressWarnings("unchecked")
	public int count() {
		List<Long> list = getHibernateTemplate().find(
				"select count(*) from " + persistentClass.getName() + " o");
		Long count = list.get(0);
		return count.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(final T exampleObject) {
		Example example = Example.create(exampleObject);
		example.enableLike(MatchMode.ANYWHERE);
		example.ignoreCase();

		DetachedCriteria c = DetachedCriteria.forClass(persistentClass).add(
				example);

		return getHibernateTemplate().findByCriteria(c);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllPaged(Paginator paginator) {

		paginator.setTotalResultsCount(count());

		DetachedCriteria c = DetachedCriteria.forClass(persistentClass);

		if (paginator.getOrder() != null && paginator.getOrderField() != null) {
			if (paginator.getOrder() == Order.asc)
				c.addOrder(org.hibernate.criterion.Order.asc(paginator
						.getOrderField()));
			else
				c.addOrder(org.hibernate.criterion.Order.desc(paginator
						.getOrderField()));
		}

		return getHibernateTemplate().findByCriteria(c,
				paginator.getResultsPerPage() * (paginator.getPageIndex() - 1),
				paginator.getResultsPerPage());
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExamplePaged(T exampleObject, Paginator paginator) {

		// TODO Hardcoded count, it must do just a COUNT, not retrieve the data
		paginator.setTotalResultsCount(findByExample(exampleObject).size());

		Example example = Example.create(exampleObject);
		example.enableLike(MatchMode.ANYWHERE);
		example.ignoreCase();
		// example.excludeProperty("property1");

		DetachedCriteria c = DetachedCriteria.forClass(persistentClass).add(
				example);

		if (paginator.getOrder() != null && paginator.getOrderField() != null) {
			if (paginator.getOrder() == Order.asc)
				c.addOrder(org.hibernate.criterion.Order.asc(paginator
						.getOrderField()));
			else
				c.addOrder(org.hibernate.criterion.Order.desc(paginator
						.getOrderField()));
		}

		return getHibernateTemplate().findByCriteria(c,
				paginator.getResultsPerPage() * (paginator.getPageIndex() - 1),
				paginator.getResultsPerPage());
	}
}
