package com.fileshare.infrastructure.networking.server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.fileshare.infrastructure.networking.client.ClientConnection;

public class RoomRegistry {

    private final Map<String, List<ClientConnection>> rooms = new ConcurrentHashMap<>();

    public void addClient(String roomCode,ClientConnection clientConnection) {

        rooms.computeIfAbsent(roomCode,key -> new CopyOnWriteArrayList<>());

        rooms.get(roomCode).add(clientConnection);
    }

    public List<ClientConnection> getClients(String roomCode) {
        return rooms.getOrDefault(roomCode,List.of());
    }

    public void removeClient(String roomCode,ClientConnection clientConnection) {

        List<ClientConnection> clients = rooms.get(roomCode);

        if (clients != null) {
            clients.remove(clientConnection);
        }
    }
}