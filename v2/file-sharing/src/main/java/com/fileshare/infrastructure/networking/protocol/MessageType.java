package com.fileshare.infrastructure.networking.protocol;

public enum MessageType {

    CREATE_ROOM,
    JOIN_ROOM,

    FILE_TRANSFER_REQUEST,
    FILE_TRANSFER_ACCEPTED,
    FILE_TRANSFER_REJECTED,

    TRANSFER_PROGRESS,
    TRANSFER_COMPLETED,
    TRANSFER_CANCELLED,

    HEARTBEAT,
    DISCONNECT
}