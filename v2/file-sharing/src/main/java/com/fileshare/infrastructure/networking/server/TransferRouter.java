package com.fileshare.infrastructure.networking.server;

import java.util.UUID;

import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public class TransferRouter {

    private final ClientRegistry clientRegistry;

    public TransferRouter(
            ClientRegistry clientRegistry) {

        this.clientRegistry = clientRegistry;
    }

    public boolean routeToPeer(
            UUID receiverPeerId,
            NetworkMessage message) {

        ConnectionContext receiver =
                clientRegistry.find(receiverPeerId);

        if (receiver == null) {
            return false;
        }

        try {

            receiver.getConnection()
                    .write(message);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}