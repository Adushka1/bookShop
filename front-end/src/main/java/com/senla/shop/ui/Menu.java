package com.senla.shop.ui;

import java.util.List;

public class Menu {
    private String name;
    private List<MenuItem> menuItems;


    public Menu(String name, List<MenuItem> menuItems) {
        this.name = name;
        this.menuItems = menuItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    @Override
    public String toString() {
        return name;
    }
}
