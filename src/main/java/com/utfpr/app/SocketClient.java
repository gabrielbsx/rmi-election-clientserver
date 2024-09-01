package com.utfpr.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 3005;

        try (Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            byte[] buffer = new byte[1024];

            String userInput;
            int bytesRead;

            System.out.println("Conectado com o servidor");

            System.out.print("Comando: ");

            while (true) {
                userInput = consoleInput.readLine();

                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }

                output.write(userInput.getBytes());
                output.flush();

                bytesRead = socket.getInputStream().read(buffer);

                if (bytesRead == -1) {
                    System.out.println("Server closed the connection.");
                    break;
                }

                String serverResponse = new String(buffer, 0, bytesRead);
                System.out.print("Servidor: " + serverResponse.trim());
            }

        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
        }
    }
}
