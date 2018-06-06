package com.adushka.shop.dao;

import com.adushka.shop.model.Reader;

public class ReaderDao extends GenericDao<Reader> implements IReaderDao {

    public ReaderDao() {
        super(Reader.class);
    }
}
