package com.adushka.shop.manager;

import com.adushka.shop.executor.Executor;
import com.adushka.shop.model.Order;


import java.util.List;

public class OrderManager implements IOrderManager {

    private IOrderDao orderDao;

    public OrderManager() {
        orderDao = (IOrderDao) DependencyInjection.getInstance().getObject(IOrderDao.class);
    }

    @Override
    public void create(Order order) throws Exception {
        Executor.transact(session -> {
            orderDao.create(session, order);
            return null;
        });
    }

    @Override
    public void delete(int id) throws Exception {
        Executor.transact(session -> {
            Order order = orderDao.getById(session, id);
            if (order != null){
                orderDao.delete(session, order);
            }
            return null;
        });
    }

    @Override
    public Order getById(int id) throws Exception {
        return Executor.transact(session -> orderDao.getById(session, id));
    }

    @Override
    public List<Order> getAll(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> orderDao.getAll(session, sorting));
    }

    @Override
    public List<Order> getAllExec(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> orderDao.getAllExec(session, sorting));
    }

    @Override
    public void cancel(int id) {
        //orderDao.update(id);
    }

    @Override
    public Integer getAllPrice() throws Exception {
        return Executor.transact(session -> orderDao.getAllPrice(session));
    }

    @Override
    public Integer getAmountExecutedOrders() throws Exception {
        return Executor.transact(session -> orderDao.getAmountExecutedOrders(session));
    }

    @Override
    public void finishOrder(){
    }

    public void clone(int id) throws Exception {
        Executor.transact(session -> {
            Order order = orderDao.getById(session, id);
            assert order != null;
            orderDao.create(session, order.clone());
            return null;
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() throws Exception {
        List<String> lines = CSVWorker.loadCsvStrings(Order.class);

        Executor.transact(session -> {
            for (Order order: (List<Order>) Parser.parse(Order.class, lines)){
                orderDao.saveOrUpdate(session, order);
            }
            return null;
        });
    }

    @Override
    public void exportToCsv() throws Exception {
        CSVWorker.saveToCSV(getAll(null));
    }
}
