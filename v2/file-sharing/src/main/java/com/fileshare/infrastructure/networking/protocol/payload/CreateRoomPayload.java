package com.fileshare.infrastructure.networking.protocol.payload;

public record CreateRoomPayload(
        String username,
        String ipAddress) {
}