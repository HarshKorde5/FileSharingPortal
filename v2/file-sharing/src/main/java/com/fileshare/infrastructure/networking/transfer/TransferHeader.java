package com.fileshare.infrastructure.networking.transfer;

import java.util.UUID;

public record TransferHeader(
        UUID transferId,
        String fileName,
        long fileSize) {
}