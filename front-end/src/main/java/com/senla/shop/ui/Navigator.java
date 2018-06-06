package com.senla.shop.ui;

import com.senla.shop.util.Printer;

public class Navigator {
    private Menu currentMenu;

    public Navigator(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void printMenu() {
        int i = 0;
        Printer.print("--- " + currentMenu + " ---");
        for (MenuItem item: currentMenu.getMenuItems()){
            if (item.getTitle().equals("exit")){
                Printer.print(0 + " -- " + item);
                continue;
            }
            Printer.print(++i + " -- " + item);
        }
    }

    public void navigate(Integer index) {
        if (index >= 0 && index < currentMenu.getMenuItems().size()) {
            MenuItem menuItem = currentMenu.getMenuItems().get(index);
            if (menuItem.getAction() == null) {
                currentMenu = menuItem.getNextMenu();
                return;
            }
            menuItem.doAction();
            return;
        }
        Printer.print("There's no such item");
    }
}
