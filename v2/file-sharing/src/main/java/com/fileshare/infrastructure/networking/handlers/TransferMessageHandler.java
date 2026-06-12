package com.fileshare.infrastructure.networking.handlers;

import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public interface TransferMessageHandler {

    void handle(NetworkMessage message);
}