package com.adushka.shop.api.facade;

import com.adushka.shop.model.Book;
import com.adushka.shop.model.Order;
import com.adushka.shop.model.Reader;

import java.util.List;

public interface IFacade {
    void addReader(Reader reader);
    void addBook(Book book);
    void addOrder(Order order);
    void cloneOrder(int id);
    void cancelOrder(int id);
    Integer getAllPrice();
    Integer getAmountExecutedOrders();
    Book getBook(Integer id);
    Order getOrder(Integer id);
    Reader getReader(Integer id);
    List<Book> sortBooksBy(SortingType type);
    List<Book> sortUnsoldBooksBy(SortingType type);
    List<Order> sortOrdersBy(SortingType type);
    List<Order> sortExecutedOrdersBy(SortingType type);
    List<Book> sortRequestsBy(SortingType type);
    List<Reader> sortReadersBy(SortingType type);
    void finishOrder();
    void importBooks();
    void importOrders();
    void importReaders();
    void exportBooks();
    void exportOrders();
    void exportReaders();
}
