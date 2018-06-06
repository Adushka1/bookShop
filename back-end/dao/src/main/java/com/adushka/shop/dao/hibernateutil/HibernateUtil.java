package com.adushka.shop.dao.hibernateutil;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static HibernateUtil instance;
    private SessionFactory sessionFactory;
    private final static Logger LOGGER = Logger.getLogger(HibernateUtil.class);


    private HibernateUtil(){
        buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        if (instance == null) {
            instance = new HibernateUtil();
        }
        return instance;
    }

    private void buildSessionFactory() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            LOGGER.error("Initial SessionFactory creation failed.", ex);
        }
    }
    
    public SessionFactory getSessionFactory(){
        if (sessionFactory == null || sessionFactory.isClosed()){
            buildSessionFactory();
        }
        return sessionFactory;
    }
}
