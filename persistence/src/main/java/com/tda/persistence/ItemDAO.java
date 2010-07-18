package com.tda.persistence;

import com.tda.model.Item;

public class ItemDAO extends GenericDAOImpl<Item> implements GenericDAO<Item> {
	@Override
    protected Class<Item> getDomainClass() {
        return Item.class;
    }
}
