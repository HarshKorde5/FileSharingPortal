package com.fileshare.infrastructure.networking.protocol.payload;

import java.util.UUID;

public record FileTransferRejectedPayload(
        String roomCode,
        UUID senderPeerId,
        UUID receiverPeerId,
        String fileName,
        String reason) {
}