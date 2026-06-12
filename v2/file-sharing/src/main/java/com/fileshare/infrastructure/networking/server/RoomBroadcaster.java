package com.fileshare.infrastructure.networking.server;

import java.util.List;

import com.fileshare.infrastructure.networking.client.ClientConnection;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public class RoomBroadcaster {

    private final RoomRegistry roomRegistry;

    public RoomBroadcaster(RoomRegistry roomRegistry) {
        this.roomRegistry = roomRegistry;
    }

    public void broadcast(String roomCode,NetworkMessage message) {

        List<ClientConnection> clients = roomRegistry.getClients(roomCode);

        for (ClientConnection client : clients) {
            try {
                client.write(message);
            } catch (Exception ignored) {}
        }
    }
}