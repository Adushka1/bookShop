package com.senla.shop.ui.actions.order;

import com.senla.shop.main.Client;
import com.senla.shop.model.*;
import com.senla.shop.ui.actions.IAction;
import com.senla.shop.util.Input;

import java.util.*;

public class AdditionOrder implements IAction {
    @Override
    public void execute() {
        List<Object> parameters = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        List<Reader> readers;

        String title;
        int price;
        Date datePublished;
        Date dateReceipted;

        String answer;
        do {
            title = Input.nextLine("Input the title: ");
            price = Input.nextInt("Input the price: ");
            datePublished = Input.nextDate("Input the date published: ");
            dateReceipted = Input.nextDate("Input the date receipted: ");
            readers = new ArrayList<>();

            do {
                String readerName = Input.nextLine("Input the reader's name: ");
                readers.add(new Reader(readerName));
                answer = Input.nextLine("Can you add yet reader?\n 1 -- yes\n 2 -- no\nPlease write answer: ");
            } while (answer.equals("1"));

            books.add(new Book(title, price, false, datePublished, dateReceipted));

            answer = Input.nextLine("Can you add yet book?\n 1 -- yes\n 2 -- no\nPlease write answer: ");
        } while (answer.equals("1"));

        Date dateExecuted = Input.nextDate("Input the date executed: ");

        String readerName = Input.nextLine("Input the reader's name: ");

        parameters.add(new Order(new Reader(readerName), dateExecuted, books));

        Map<String, List<Object>> request = new HashMap<>();
        request.put("addOrder", parameters);
        Client.send(request);
    }
}
