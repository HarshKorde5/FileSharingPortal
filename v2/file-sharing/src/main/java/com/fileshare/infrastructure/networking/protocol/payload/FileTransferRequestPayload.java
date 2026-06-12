package com.fileshare.infrastructure.networking.protocol.payload;

import java.util.UUID;

public record FileTransferRequestPayload(
        UUID transferId,
        String roomCode,
        UUID senderPeerId,
        UUID receiverPeerId,
        String fileName,
        long fileSize) {
}