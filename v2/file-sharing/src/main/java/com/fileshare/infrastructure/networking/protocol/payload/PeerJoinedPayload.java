package com.fileshare.infrastructure.networking.protocol.payload;

public record PeerJoinedPayload(
        String roomCode,
        String username) {
}