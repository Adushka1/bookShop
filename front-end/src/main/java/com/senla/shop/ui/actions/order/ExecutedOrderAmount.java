package com.senla.shop.ui.actions.order;

import com.senla.shop.main.Client;
import com.senla.shop.ui.actions.IAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.senla.shop.util.Printer.print;

public class ExecutedOrderAmount implements IAction {
    @Override
    public void execute() {

        Map<String, List<Object>> request = new HashMap<>();
        request.put("getAmountExecutedOrders", null);
        print(Client.send(request));
    }
}