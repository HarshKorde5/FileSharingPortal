package com.fileshare.application.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fileshare.domain.enums.PeerRole;
import com.fileshare.domain.interfaces.RoomService;
import com.fileshare.domain.models.Peer;
import com.fileshare.domain.models.Room;

public class InMemoryRoomService implements RoomService {

    private final Map<String, Room> rooms = new HashMap<>();

    @Override
    public Room createRoom(String username, String ipAddress) {

        Peer host = new Peer(UUID.randomUUID(),username,ipAddress,PeerRole.HOST);

        String roomCode = UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        Room room = new Room(UUID.randomUUID(),roomCode,host);

        rooms.put(roomCode, room);

        return room;
    }
}