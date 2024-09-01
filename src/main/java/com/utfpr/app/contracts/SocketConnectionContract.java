package com.utfpr.app.contracts;

public interface SocketConnectionContract extends ConnectionContract {

    void connect();

    void disconnect();

    void send(String message);

    String receive();

    void handle();
}
