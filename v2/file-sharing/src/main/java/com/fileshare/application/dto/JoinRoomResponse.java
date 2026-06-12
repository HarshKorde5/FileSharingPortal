package com.fileshare.application.dto;

import java.util.UUID;

public record JoinRoomResponse(
        String roomCode,
        String username,
        UUID peerId,
        int memberCount) {
}