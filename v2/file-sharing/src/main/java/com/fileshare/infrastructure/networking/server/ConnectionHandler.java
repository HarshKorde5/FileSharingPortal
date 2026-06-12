package com.fileshare.infrastructure.networking.server;

import com.fileshare.infrastructure.networking.client.ClientConnection;
import com.fileshare.infrastructure.networking.handlers.MessageProcessor;
import com.fileshare.infrastructure.networking.protocol.MessageFactory;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public class ConnectionHandler implements Runnable {

    private final ClientConnection clientConnection;
    private final MessageProcessor processor;

    public ConnectionHandler(ClientConnection clientConnection, MessageProcessor processor) {
        this.clientConnection = clientConnection;
        this.processor = processor;
    }

    @Override
    public void run() {

        System.out.println("Client connected: "+ clientConnection.getRemoteAddress());

        NetworkMessage response = processor.process(MessageFactory.heartbeat());
        System.out.println(response);
    }
}