package com.adushka.shop.executor;

import com.adushka.shop.dao.hibernateutil.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Executor {

    private final static Logger LOGGER = Logger.getLogger(Executor.class);

    public static <T> T transact(Command<T> command) throws Exception {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            T t = command.process(session);
            transaction.commit();
            return t;
        } catch (HibernateException e) {
            LOGGER.error("Commit is failed", e);
            if(transaction != null){
                transaction.rollback();
            }
            throw new Exception("Transaction is failed", e);
        }
    }
}
