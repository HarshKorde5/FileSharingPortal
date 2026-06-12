package com.fileshare.application.dto;

import java.util.UUID;

public record CreateRoomResponse(
        String roomCode,
        String hostUsername,
        UUID peerId) {
}