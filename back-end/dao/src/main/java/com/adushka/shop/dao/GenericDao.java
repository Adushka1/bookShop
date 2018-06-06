package com.adushka.shop.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.List;

public abstract class GenericDao<T> implements IGenericDao<T> {

    private Class<T> clazz;

    protected GenericDao(Class clazz){
        this.clazz = clazz;
    }

    @Override
    public void create(Session session, T t) {
        session.save(t);
    }

    @Override
    public void saveOrUpdate(Session session, T t) {
        session.saveOrUpdate(t);
    }

    @Override
    public void delete(Session session, T t) {
        session.delete(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(Session session, int id) {
        return session.get(clazz, id);
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public List<T> getAll(Session session, String sort) {
        Criteria criteria = session.createCriteria(clazz);
        return criteria.addOrder(Order.asc(sort)).list();
    }
}
