package com.fileshare.infrastructure.networking.protocol.payload;

import java.util.UUID;

public record JoinRoomSuccessPayload(
        String roomCode,
        String username,
        UUID peerId,
        int memberCount) {
}