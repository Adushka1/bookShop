package com.senla.shop.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import static com.senla.shop.util.Printer.print;

public class Client {
    private static ObjectInputStream in;
    private static ObjectOutputStream out;

    public Client(Socket socket) throws IOException {
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    public static Object send(Map<String, List<Object>> request) {
        try {
            out.writeObject(request);
            out.flush();
            return in.readObject();
        } catch (Exception e) {
            print(e.getMessage());
        }
        return null;
    }

    public static void close() throws IOException {
        in.close();
        out.close();
    }
}
