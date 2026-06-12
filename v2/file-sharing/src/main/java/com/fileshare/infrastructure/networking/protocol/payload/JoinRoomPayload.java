package com.fileshare.infrastructure.networking.protocol.payload;

public record JoinRoomPayload(
        String roomCode,
        String username,
        String ipAddress) {
}