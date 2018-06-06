package com.senla.shop.ui.actions.book;

import com.senla.shop.main.Client;
import com.senla.shop.model.Book;
import com.senla.shop.ui.actions.IAction;
import com.senla.shop.util.Input;

import java.util.*;

public class AdditionBook implements IAction {
    @Override
    public void execute() {
        List<Object> parameters = new ArrayList<>();

        String title = Input.nextLine("Input the title: ");
        Integer price = Input.nextInt("Input the price: ");
        Date datePublished = Input.nextDate("Input the date published: ");
        Date dateReceipted = Input.nextDate("Input the date receipted: ");

        parameters.add(new Book(title, price, true, datePublished, dateReceipted));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("addBook", parameters);
        Client.send(request);
    }
}