package com.utfpr.app.services;

import java.io.IOException;
import java.net.Socket;

import com.utfpr.app.contracts.SocketConnectionContract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocketClientConnectionImpl implements SocketConnectionContract {

    private String hostname;
    private int port;
    private static Socket _socket;

    @Override
    public void connect() {
        try {
            Socket socket = new Socket(this.hostname, this.port);

            _socket = socket;

            System.out.println("Connected to the server!");

            handle();
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
        }
    }

    @Override
    public void disconnect() {
    }

    @Override
    public void send(String message) {
    }

    @Override
    public String receive() {
        return "";
    }

    @Override
    public void handle() {
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while (true) {
                bytesRead = _socket.getInputStream().read(buffer);

                if (bytesRead == -1) {
                    System.out.println("Server closed the connection.");
                    break;
                }

                String serverResponse = new String(buffer, 0, bytesRead);

                System.out.print("Server: " + serverResponse.trim());
            }
        } catch (IOException e) {
            System.out.println("Client exception: " + e.getMessage());
        }
    }

    @Override
    public void start() {
        connect();
    }

}
