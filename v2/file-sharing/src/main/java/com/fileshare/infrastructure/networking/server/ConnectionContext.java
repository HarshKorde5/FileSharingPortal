package com.fileshare.infrastructure.networking.server;

import java.util.UUID;

import com.fileshare.infrastructure.networking.client.ClientConnection;

public class ConnectionContext {

    private final ClientConnection connection;

    private UUID peerId;
    private String username;
    private String roomCode;

    public ConnectionContext(ClientConnection connection) {
        this.connection = connection;
    }

    public ClientConnection getConnection() {
        return connection;
    }

    public UUID getPeerId() {
        return peerId;
    }

    public void setPeerId(UUID peerId) {
        this.peerId = peerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
}