package com.adushka.shop.manager;

import com.adushka.shop.executor.Executor;
import com.adushka.shop.model.Reader;


import java.util.List;

public class ReaderManager implements IReaderManager {

    private IReaderDao readerDao;

    public ReaderManager() {
        readerDao = (IReaderDao) DependencyInjection.getInstance().getObject(IReaderDao.class);
    }

    @Override
    public void create(Reader reader) throws Exception {
        Executor.transact(session -> {
            readerDao.create(session, reader);
            return null;
        });
    }

    @Override
    public void delete(int id) throws Exception {
        Executor.transact(session -> {
            Reader reader = readerDao.getById(session, id);
            if (reader != null){
                readerDao.delete(session, reader);
            }
            return null;
        });
    }

    @Override
    public Reader getById(int id) throws Exception {
        return Executor.transact(session -> readerDao.getById(session, id));
    }

    @Override
    public List<Reader> getAll(String sort) throws Exception {
        if (sort == null){
            sort = "id";
        }
        String sorting = sort;
        return Executor.transact(session -> readerDao.getAll(session, sorting));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void importFromCsv() throws Exception {
        List<String> lines = CSVWorker.loadCsvStrings(Reader.class);

        Executor.transact(session -> {
            for (Reader reader: (List<Reader>) Parser.parse(Reader.class, lines)){
                readerDao.saveOrUpdate(session, reader);
            }
            return null;
        });
    }

    @Override
    public void exportToCsv() throws Exception {
        CSVWorker.saveToCSV(getAll(null));
    }
}
