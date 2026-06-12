package com.fileshare.infrastructure.networking.server;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ClientRegistry {

    private final Map<UUID, ConnectionContext> clients = new ConcurrentHashMap<>();

    public void register(UUID peerId,ConnectionContext context) {
        clients.put(peerId, context);
    }

    public ConnectionContext find(UUID peerId) {
        return clients.get(peerId);
    }

    public void unregister(UUID peerId) {
        clients.remove(peerId);
    }

    public boolean contains(UUID peerId) {
        return clients.containsKey(peerId);
    }

    public ConnectionContext findOrThrow(UUID peerId) {
        ConnectionContext context = clients.get(peerId);

        if (context == null) {
            throw new IllegalArgumentException("Peer not connected: " + peerId);
        }

        return context;
    }
}