package com.adushka.shop.dao;

import com.adushka.shop.model.Book;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class BookDao extends GenericDao<Book> implements IBookDao {

    public BookDao() {
        super(Book.class);
    }

    @SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public List<Book> getAllUnsold(Session session, String sort) {
        LocalDate date = LocalDate.now().minusMonths(6);

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root).where(cb.lessThan(root.get("dateReceipted"), date)).orderBy(cb.asc(root.get(sort)));

        return session.createQuery(criteria).getResultList();
    }
}
