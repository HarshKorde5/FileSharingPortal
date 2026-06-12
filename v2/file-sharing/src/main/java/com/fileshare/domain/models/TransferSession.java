package com.fileshare.domain.models;

import java.util.Objects;
import java.util.UUID;

import com.fileshare.domain.enums.TransferStatus;

public class TransferSession {

    private final UUID transferId;
    private final UUID roomId;
    private final Peer sender;
    private final Peer receiver;
    private final SharedFile sharedFile;
    private TransferStatus status;
    private int progressPercentage;

    public TransferSession(UUID transferId,UUID roomId,Peer sender,Peer receiver,SharedFile sharedFile) {

        this.transferId = Objects.requireNonNull(transferId,"Transfer ID cannot be null.");

        this.roomId = Objects.requireNonNull(roomId,"Room ID cannot be null.");

        this.sender = Objects.requireNonNull(sender,"Sender cannot be null.");

        this.receiver = Objects.requireNonNull(receiver,"Receiver cannot be null.");

        this.sharedFile = Objects.requireNonNull(sharedFile,"Shared file cannot be null.");

        this.status = TransferStatus.PENDING;
        this.progressPercentage = 0;
    }

    public UUID getTransferId() {
        return transferId;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public Peer getSender() {
        return sender;
    }

    public Peer getReceiver() {
        return receiver;
    }

    public SharedFile getSharedFile() {
        return sharedFile;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public void updateStatus(TransferStatus status) {
        this.status = Objects.requireNonNull(status,"Transfer status cannot be null.");
    }

    public void updateProgress(int progressPercentage) {

        if (progressPercentage < 0 || progressPercentage > 100) {
            throw new IllegalArgumentException("Progress must be between 0 and 100.");
        }

        this.progressPercentage = progressPercentage;
    }

    @Override
    public String toString() {
        return "TransferSession{" +
                "transferId=" + transferId +
                ", roomId=" + roomId +
                ", sender=" + sender.getUsername() +
                ", receiver=" + receiver.getUsername() +
                ", file=" + sharedFile.fileName() +
                ", status=" + status +
                ", progress=" + progressPercentage +
                "%}";
    }
}