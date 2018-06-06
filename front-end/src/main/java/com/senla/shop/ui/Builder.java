package com.senla.shop.ui;

import com.senla.shop.ui.actions.book.*;
import com.senla.shop.ui.actions.order.*;
import com.senla.shop.ui.actions.request.*;

import java.util.ArrayList;
import java.util.List;

public class Builder {
    private Menu rootMenu;


    private Menu buildBookMenu() {
        List<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("Add book", new AdditionBook()));
        items.add(new MenuItem("Delete book", new DeletingBook()));
        items.add(new MenuItem("Show book info", new BookInfo()));
        items.add(new MenuItem("Show books sorted by alphabet", new SortingBooksByAlphabet()));
        items.add(new MenuItem("Show books sorted by date", new SortingBooksByDate()));
        items.add(new MenuItem("Show books sorted by price", new SortingBooksByPrice()));
        items.add(new MenuItem("Show books sorted by is store", new SortingBooksByStore()));
        items.add(new MenuItem("Show unsold books sorted by date", new SortingUnsoldBooksByDate()));
        items.add(new MenuItem("Show unsold books sorted by price", new SortingUnsoldBooksByPrice()));
        items.add(new MenuItem("Export book", new ExportBook()));
        items.add(new MenuItem("Import book", new ImportBook()));
        items.add(new MenuItem("To the beginning", rootMenu));
        return new Menu("BookMenu", items);
    }

    private Menu buildOrderMenu() {
        List<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("Add order", new AdditionOrder()));
        items.add(new MenuItem("Clone order", new CloningOrder()));
        items.add(new MenuItem("Cancel order", new CancellationOrder()));
        items.add(new MenuItem("Finish order", new FinishedOrder()));
        items.add(new MenuItem("Show order info", new OrderInfo()));
        items.add(new MenuItem("Show all price", new AllPrice()));
        items.add(new MenuItem("Show amount of executed order", new ExecutedOrderAmount()));
        items.add(new MenuItem("Show orders sorted by date", new SortingOrdersByDate()));
        items.add(new MenuItem("Show orders sorted by price", new SortingOrdersByPrice()));
        items.add(new MenuItem("Show orders sorted by status", new SortingOrdersByStatus()));
        items.add(new MenuItem("Show executed orders sorted by date", new SortingExecutedOrdersByDate()));
        items.add(new MenuItem("Show executed orders sorted by price", new SortingExecutedOrdersByPrice()));
        items.add(new MenuItem("Export order", new ExportOrder()));
        items.add(new MenuItem("Import order", new ImportOrder()));
        items.add(new MenuItem("To the beginning", rootMenu));
        return new Menu("OrderMenu", items);
    }

    private Menu buildRequestMenu() {
        List<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("Add request", new AdditionRequest()));
        items.add(new MenuItem("Show request sorted by alphabet", new SortingRequestsByAlphabet()));
        items.add(new MenuItem("Show request sorted by amount", new SortingRequestsByAmount()));
        items.add(new MenuItem("Export request", new ExportRequest()));
        items.add(new MenuItem("Import request", new ImportRequest()));
        items.add(new MenuItem("To the beginning", rootMenu));
        return new Menu("RequestMenu", items);
    }

    public Menu buildMenu() {
        List<MenuItem> items = new ArrayList<>();

        items.add(new MenuItem("books"));
        items.add(new MenuItem("orders"));
        items.add(new MenuItem("requests"));
        items.add(new MenuItem("exit"));

        rootMenu = new Menu("Menu", items);
        items.get(0).setNextMenu(buildBookMenu());
        items.get(1).setNextMenu(buildOrderMenu());
        items.get(2).setNextMenu(buildRequestMenu());
        return rootMenu;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}
