package com.adushka.shop.api.manager;

import com.adushka.shop.model.Book;

import java.util.List;

public interface IBookManager extends IManager<Book> {
    List<Book> getAllUnsold(String sort) throws Exception;
}
