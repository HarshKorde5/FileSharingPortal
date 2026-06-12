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

        try {

            while (true) {

                NetworkMessage request = clientConnection.read();

                if (request == null) {
                    break;
                }

                NetworkMessage response = processor.process(request);

                clientConnection.write(response);
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {
                clientConnection.close();
            } catch (Exception ignored) {
            }
        }
    }
}