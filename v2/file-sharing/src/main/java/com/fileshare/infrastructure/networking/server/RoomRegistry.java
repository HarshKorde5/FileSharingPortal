package com.fileshare.infrastructure.networking.server;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class RoomRegistry {

    private final Map<String, List<ConnectionContext>> rooms = new ConcurrentHashMap<>();

    public void addClient(String roomCode,ConnectionContext context) {

        rooms.computeIfAbsent(roomCode,key -> new CopyOnWriteArrayList<>());

        rooms.get(roomCode).add(context);
    }

    public List<ConnectionContext> getClients(String roomCode) {
        return rooms.getOrDefault(roomCode,List.of());
    }

    public void removeClient(String roomCode,ConnectionContext context) {

        List<ConnectionContext> clients = rooms.get(roomCode);

        if (clients != null) {
            clients.remove(context);
        }
    }

    public boolean roomExists(String roomCode) {

        return rooms.containsKey(roomCode);
    }

    public int roomSize(String roomCode) {

        return getClients(roomCode).size();
    }

}