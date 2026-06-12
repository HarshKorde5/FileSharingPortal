package com.fileshare.infrastructure.networking.client;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection {

    private final Socket socket;

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getRemoteAddress() {
        return socket.getInetAddress().getHostAddress();
    }

    public void close() throws IOException {
        socket.close();
    }
}