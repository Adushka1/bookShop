package com.adushka.shop.api.dao;

import org.hibernate.Session;

import java.util.List;

public interface IGenericDao<T> {
    void create(Session session, T t);

    void saveOrUpdate(Session session, T t);

    void delete(Session session, T t);

    T getById(Session session, int id);

    List<T> getAll(Session session, String sort);
}
