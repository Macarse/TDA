package com.tda.persistence;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class GenericDAOImpl<T> extends HibernateDaoSupport implements GenericDAO<T> {
    protected Class<T> persistentClass = getDomainClass();

    protected abstract Class<T> getDomainClass();
    
    public void save(final T entity) {
        getHibernateTemplate().save(entity);
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
        return getHibernateTemplate().find("from " + this.persistentClass.getName() + " o");
    }

    public void deleteById(final Long id) {
        T obj = this.findById(id);
        getHibernateTemplate().delete(obj);
    }

    @SuppressWarnings("unchecked")
    public int count() {
        List<Long> list = getHibernateTemplate().find("select count(*) from " + persistentClass.getName() + " o");
        Long count = list.get(0);
        return count.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(final T exampleObject) {
        return getHibernateTemplate().findByExample(exampleObject);
    }
}
