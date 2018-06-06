package com.adushka.shop.server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class Main {
    private static final int PORT = 1335;
    private final static Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
            LOGGER.trace("Started, waiting for connection");
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (IOException e){
            LOGGER.error("Failed socket connection", e);
        }
    }
}
