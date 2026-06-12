package com.fileshare.infrastructure.networking.protocol;

import java.util.Objects;

public record NetworkMessage(MessageType type,String payload) {

    public NetworkMessage {

        Objects.requireNonNull(type,"Message type cannot be null.");

        if (payload == null) {
            payload = "";
        }
    }
}