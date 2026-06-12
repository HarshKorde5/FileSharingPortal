package com.fileshare.infrastructure.networking.protocol.payload;

public record PeerLeftPayload(
        String roomCode,
        String username) {
}