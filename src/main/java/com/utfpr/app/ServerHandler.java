package com.utfpr.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerHandler extends Thread {

    private final Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            OutputStream output = this.socket.getOutputStream();

            while (true) {
                String clientMessage;

                byte[] buffer = new byte[1024];

                int bytesRead = this.socket.getInputStream().read(buffer);

                if (bytesRead == -1) {
                    break;
                }

                clientMessage = new String(buffer, 0, bytesRead);

                System.out.println("Received: " + clientMessage);

                HandleMessage handleMessage = new HandleMessage(this.socket);

                handleMessage.handle(clientMessage.trim());

                if (clientMessage.equalsIgnoreCase("exit")) {
                    output.write("Connection closed.\n".getBytes());
                    break;
                }
            }

            socket.close();
            System.out.println("Client disconnected");

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }
}
