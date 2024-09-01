package com.utfpr.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) {
        int port = 3005;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Connection created");

            while (true) {
                Socket socket = serverSocket.accept();

                new ServerHandler(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }
}
