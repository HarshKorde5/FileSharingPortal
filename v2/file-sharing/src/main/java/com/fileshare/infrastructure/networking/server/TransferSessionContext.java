package com.fileshare.infrastructure.networking.server;

import java.util.UUID;

public class TransferSessionContext {

    private final UUID transferId;
    private final UUID senderPeerId;
    private final UUID receiverPeerId;
    private final int transferPort;

    public TransferSessionContext(
            UUID transferId,
            UUID senderPeerId,
            UUID receiverPeerId,
            int transferPort) {

        this.transferId = transferId;
        this.senderPeerId = senderPeerId;
        this.receiverPeerId = receiverPeerId;
        this.transferPort = transferPort;
    }

    public UUID getTransferId() {
        return transferId;
    }

    public UUID getSenderPeerId() {
        return senderPeerId;
    }

    public UUID getReceiverPeerId() {
        return receiverPeerId;
    }

    public int getTransferPort() {
        return transferPort;
    }
}