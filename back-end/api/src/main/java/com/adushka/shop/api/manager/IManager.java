package com.adushka.shop.api.manager;

import java.util.List;

public interface IManager<T> {
    void create(T t) throws Exception;

    void delete(int id) throws Exception;

    T getById(int id) throws Exception;

    List<T> getAll(String sort) throws Exception;

    void importFromCsv() throws Exception;

    void exportToCsv() throws Exception;
}
