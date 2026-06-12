package com.fileshare.infrastructure.networking.protocol.payload;

import java.util.UUID;

public record CreateRoomSuccessPayload(
        String roomCode,
        String hostUsername,
        UUID peerId) {}