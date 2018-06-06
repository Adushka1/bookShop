package com.senla.shop.ui.actions.book;

import com.senla.shop.main.Client;
import com.senla.shop.enums.SortingType;
import com.senla.shop.model.Book;
import com.senla.shop.ui.actions.IAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.senla.shop.util.Printer.printArray;

public class SortingBooksByDate implements IAction {
    @Override
    public void execute() {

        List<Object> parameters = new ArrayList<>();
        parameters.add(SortingType.DATE);

        Map<String, List<Object>> request = new HashMap<>();
        request.put("sortBooksBy", parameters);
        printArray((List<Book>) Client.send(request));
    }
}