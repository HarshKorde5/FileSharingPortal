package com.fileshare.infrastructure.networking.server;

import com.fileshare.infrastructure.networking.client.ClientConnection;

public class ConnectionHandler implements Runnable {

    private final ClientConnection clientConnection;

    public ConnectionHandler(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    @Override
    public void run() {
        System.out.println("Client connected: "+ clientConnection.getRemoteAddress());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}