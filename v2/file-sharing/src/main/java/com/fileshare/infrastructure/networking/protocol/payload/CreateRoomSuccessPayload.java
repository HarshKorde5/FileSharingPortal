package com.fileshare.infrastructure.networking.protocol.payload;

public record CreateRoomSuccessPayload(
        String roomCode,
        String hostUsername) {
}