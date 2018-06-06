package com.adushka.shop.view;

import com.adushka.shop.api.facade.IFacade;
import com.adushka.shop.api.manager.IBookManager;
import com.adushka.shop.api.manager.IOrderManager;
import com.adushka.shop.api.manager.IReaderManager;
import com.adushka.shop.model.Book;
import com.adushka.shop.model.Order;
import com.adushka.shop.model.Reader;

import org.apache.log4j.Logger;

import java.util.List;

public class Facade implements IFacade {
    private IBookManager bookManager;
    private IReaderManager readerManager;
    private IOrderManager orderManager;
    private static volatile Facade bookStore = null;
    private final static Logger LOGGER = Logger.getLogger(Facade.class);


    private Facade(){
        bookManager = (IBookManager) DependencyInjection.getInstance().getObject(IBookManager.class);
        readerManager = (IReaderManager) DependencyInjection.getInstance().getObject(IReaderManager.class);
        orderManager = (IOrderManager) DependencyInjection.getInstance().getObject(IOrderManager.class);
    }

    public static Facade getInstance() {
        if (bookStore == null) {
            synchronized (Facade.class) {
                if (bookStore == null) {
                    bookStore = new Facade();
                }
            }
        }
        return bookStore;
    }

    @Override
    public List<Book> sortBooksBy(SortingType type) {

        try {
            switch (type) {
                case ALPHABET:
                    return bookManager.getAll("title");
                case DATE:
                    return bookManager.getAll("datePublished");
                case PRICE:
                    return bookManager.getAll("price");
                case IS_STORE:
                    return bookManager.getAll("isStore");
            }
        } catch (Exception e){
            LOGGER.error("Sorting of books is failed", e);
        }
        return null;
    }

    @Override
    public List<Book> sortUnsoldBooksBy(SortingType type) {

        try {
            switch (type) {
                case DATE:
                    return bookManager.getAllUnsold("dateReceipted");
                case PRICE:
                    return bookManager.getAllUnsold("price");
            }
        } catch (Exception e){
            LOGGER.error("Sorting of unsold books is failed", e);
        }
        return null;
    }

    @Override
    public List<Order> sortOrdersBy(SortingType type) {

        try {
            switch (type) {
                case DATE:
                    return orderManager.getAll("dateExecuted");
                case PRICE:
                    return orderManager.getAll("price");
                case STATUS:
                    return orderManager.getAll("status");
            }
        } catch (Exception e){
            LOGGER.error("Sorting of orders is failed", e);
        }
        return null;
    }

    @Override
    public List<Order> sortExecutedOrdersBy(SortingType type) {

        try {
            switch (type) {
                case PRICE:
                    return orderManager.getAllExec("price");
                case DATE:
                    return orderManager.getAllExec("dateExecuted");
            }
        } catch (Exception e){
            LOGGER.error("Sorting of executed orders is failed", e);
        }
        return null;
    }

    @Override
    public List<Book> sortRequestsBy(SortingType type) {

        try {
            switch (type) {
                case AMOUNT:
                    return bookManager.getAll("amount");
                case ALPHABET:
                    return bookManager.getAll("title");
            }
        } catch (Exception e){
            LOGGER.error("Sorting of requests is failed", e);
        }
        return null;
    }

    @Override
    public List<Reader> sortReadersBy(SortingType type) {

        try {
            switch (type) {
                case ALPHABET:
                    return readerManager.getAll("name");
                case BALANCE:
                    return readerManager.getAll("balance");
            }
        } catch (Exception e){
            LOGGER.error("Sorting of readers is failed", e);
        }
        return null;
    }

    @Override
    public synchronized void addBook(Book book){
        try {
            bookManager.create(book);
        } catch (Exception e){
            LOGGER.error("Creation of book is failed", e);
        }
    }

    @Override
    public synchronized void addOrder(Order order) {
        try {
            orderManager.create(order);
        } catch (Exception e){
            LOGGER.error("Creation of order is failed", e);
        }
    }

    @Override
    public synchronized void addReader(Reader reader){
        try {
            readerManager.create(reader);
        } catch (Exception e){
            LOGGER.error("Creation of reader is failed", e);
        }
    }

    @Override
    public synchronized void cloneOrder(int id){
        try {
            orderManager.clone(id);
        } catch (Exception e){
            LOGGER.error("Cloning of order is failed", e);
        }
    }

    @Override
    public synchronized void cancelOrder(int id) {
        try {
            orderManager.cancel(id);
        } catch (Exception e){
            LOGGER.error("Cancellation of order is failed", e);
        }
    }

    @Override
    public Book getBook(Integer id) {
        try {
            return bookManager.getById(id);
        } catch (Exception e){
            LOGGER.error("getting of book is failed", e);
        }
        return null;
    }

    @Override
    public Order getOrder(Integer id) {
        try {
            return orderManager.getById(id);
        } catch (Exception e){
            LOGGER.error("getting of order is failed", e);
        }
        return null;
    }

    @Override
    public Reader getReader(Integer id) {
        try {
            return readerManager.getById(id);
        } catch (Exception e){
            LOGGER.error("getting of reader is failed", e);
        }
        return null;
    }

    @Override
    public Integer getAllPrice() {
        try {
            return orderManager.getAllPrice();
        } catch (Exception e){
            LOGGER.error("getting of all price is failed", e);
        }
        return null;

    }

    @Override
    public Integer getAmountExecutedOrders() {
        try {
            return orderManager.getAmountExecutedOrders();
        } catch (Exception e){
            LOGGER.error("getting of order's amount is failed", e);
        }
        return null;
    }

    @Override
    public void finishOrder(){
        orderManager.finishOrder();
    }

    @Override
    public void importBooks() {
        try {
            bookManager.importFromCsv();
        } catch (Exception e){
            LOGGER.error("importation of books is failed", e);
        }
    }

    @Override
    public void importOrders() {
        try {
            orderManager.importFromCsv();
        } catch (Exception e){
            LOGGER.error("importation of orders is failed", e);
        }
    }

    @Override
    public void importReaders() {
        try {
            readerManager.importFromCsv();
        } catch (Exception e){
            LOGGER.error("importation of readers is failed", e);
        }
    }

    @Override
    public void exportBooks() {
        try {
            bookManager.exportToCsv();
        } catch (Exception e){
            LOGGER.error("exportation of books is failed", e);
        }
    }

    @Override
    public void exportOrders() {
        try {
            orderManager.exportToCsv();
        } catch (Exception e){
            LOGGER.error("exportation of orders is failed", e);
        }
    }

    @Override
    public void exportReaders() {
        try {
            readerManager.exportToCsv();
        } catch (Exception e){
            LOGGER.error("exportation of readers is failed", e);
        }
    }
}
