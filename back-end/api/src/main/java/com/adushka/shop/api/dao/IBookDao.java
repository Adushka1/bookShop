package com.adushka.shop.api.dao;

import com.adushka.shop.model.Book;
import org.hibernate.Session;

import java.util.List;

public interface IBookDao extends IGenericDao<Book> {
    List<Book> getAllUnsold(Session session, String sort);
}
