package com.fileshare.infrastructure.networking.protocol.payload;

import java.util.UUID;

public record TransferOfferPayload(
        UUID transferId,
        UUID senderPeerId,
        UUID receiverPeerId,
        int transferPort,
        String fileName,
        long fileSize) {
}