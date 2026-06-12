package com.fileshare.infrastructure.networking.server;

import com.fileshare.infrastructure.networking.client.ClientConnection;
import com.fileshare.infrastructure.networking.protocol.MessageFactory;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public class ConnectionHandler implements Runnable {

    private final ClientConnection clientConnection;

    public ConnectionHandler(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    @Override
    public void run() {

        System.out.println("Client connected: "+ clientConnection.getRemoteAddress());

        NetworkMessage message = MessageFactory.heartbeat();

        System.out.println("Protocol initialized: "+ message.type());
    }
}