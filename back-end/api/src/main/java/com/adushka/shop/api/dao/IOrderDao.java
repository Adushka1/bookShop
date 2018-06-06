package com.adushka.shop.api.dao;

import com.adushka.shop.model.Order;
import org.hibernate.Session;

import java.util.List;

public interface IOrderDao extends IGenericDao<Order> {
    Integer getAllPrice(Session session);

    Integer getAmountExecutedOrders(Session session);

    List<Order> getAllExec(Session session, String sort);
}
