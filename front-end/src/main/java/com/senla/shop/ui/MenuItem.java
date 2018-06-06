package com.senla.shop.ui;

import com.senla.shop.ui.actions.IAction;

public class MenuItem {
    private String title;
    private IAction action;
    private Menu nextMenu;


    public MenuItem(String name, Menu nextMenu) {
        this.title = name;
        this.nextMenu = nextMenu;
    }

    public MenuItem(String name, IAction action) {
        this.title = name;
        this.action = action;
    }

    public MenuItem(String name) {
        this.title = name;
    }

    public void doAction() {
        action.execute();
    }

    public void setNextMenu(Menu nextMenu) {
        this.nextMenu = nextMenu;
    }

    public String getTitle() {
        return title;
    }

    public IAction getAction() {
        return action;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    @Override
    public String toString() {
        return title;
    }
}