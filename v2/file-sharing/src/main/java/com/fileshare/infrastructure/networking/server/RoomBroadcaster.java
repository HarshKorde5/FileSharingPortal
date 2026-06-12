package com.fileshare.infrastructure.networking.server;

import java.util.List;

import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public class RoomBroadcaster {

    private final RoomRegistry roomRegistry;

    public RoomBroadcaster(RoomRegistry roomRegistry) {
        this.roomRegistry = roomRegistry;
    }

    public void broadcast(String roomCode,NetworkMessage message) {
        List<ConnectionContext> clients = roomRegistry.getClients(roomCode);

        for (ConnectionContext context : clients) {
            try {
                context.getConnection().write(message);
            } catch (Exception ignored) {}
        }
    }

    public void broadcastExcept(String roomCode,ConnectionContext excludedContext,NetworkMessage message) {

    List<ConnectionContext> clients = roomRegistry.getClients(roomCode);

    for (ConnectionContext context : clients) {
        if (context == excludedContext) {
            continue;
        }

        try {
            context.getConnection().write(message);
        } catch (Exception ignored) {}
    }
}
}