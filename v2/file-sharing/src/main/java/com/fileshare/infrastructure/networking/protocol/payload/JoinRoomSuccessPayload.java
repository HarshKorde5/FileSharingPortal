package com.fileshare.infrastructure.networking.protocol.payload;

public record JoinRoomSuccessPayload(
        String roomCode,
        String username,
        int memberCount) {
}