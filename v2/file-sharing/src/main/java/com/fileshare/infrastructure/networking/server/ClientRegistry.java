package com.fileshare.infrastructure.networking.server;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.fileshare.infrastructure.networking.client.ClientConnection;

public class ClientRegistry {

    private final Map<UUID, ClientConnection> clients = new ConcurrentHashMap<>();

    public void register(UUID peerId,ClientConnection connection) {
        clients.put(peerId, connection);
    }

    public ClientConnection find(UUID peerId) {
        return clients.get(peerId);
    }

    public void unregister(UUID peerId) {
        clients.remove(peerId);
    }

    public boolean contains(UUID peerId) {
        return clients.containsKey(peerId);
    }
}