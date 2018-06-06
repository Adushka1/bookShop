package com.adushka.shop.util;

import com.adushka.shop.view.Facade;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class MethodInvoker {
    static public Object getResponse(Map<String, List<Object>> request) throws Exception {
        Object invokedMethod = null;
        if (request != null) {
            String stringMethod = request.keySet().toArray()[0].toString();
            List<Object> objectList = request.get(stringMethod);

            if (objectList == null || objectList.isEmpty()) {
                Method method = Facade.class.getMethod(stringMethod);
                invokedMethod = method.invoke(Facade.getInstance());
            } else {
                Method method = Facade.class.getMethod(stringMethod, getTypes(objectList));
                invokedMethod = method.invoke(Facade.getInstance(), objectList.toArray());
            }
        }
        return invokedMethod;
    }

    private static Class[] getTypes(List<Object> list) {
        Class[] types = new Class[list.size()];
        for (int i = 0; i < list.size(); i++) {
            types[i] = list.get(i).getClass();
        }
        return types;
    }
}
