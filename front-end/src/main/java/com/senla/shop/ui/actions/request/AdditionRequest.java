package com.senla.shop.ui.actions.request;

import com.senla.shop.model.Book;
import com.senla.shop.model.Reader;
import com.senla.shop.main.Client;
import com.senla.shop.ui.actions.IAction;
import com.senla.shop.util.Input;

import java.util.*;

public class AdditionRequest implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();
        parameters.add(Input.nextInt("Input ID reader: "));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("getReader", parameters);
        Reader reader = (Reader) Client.send(request);

        parameters = new ArrayList<>();
        parameters.add(Input.nextInt("Input ID book: "));

        request = new HashMap<>();
        request.put("getBook", parameters);
        Book book = (Book) Client.send(request);

        parameters = new ArrayList<>();
        parameters.add(book);
        parameters.add(reader);
        request = new HashMap<>();
        request.put("addRequest", parameters);
        Client.send(request);
    }
}
