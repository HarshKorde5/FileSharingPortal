package com.fileshare.infrastructure.networking.protocol;

public class MessageDeserializer {

    public NetworkMessage deserialize(String data) {

        String[] parts = data.split(";", 2);

        MessageType type = MessageType.valueOf(parts[0]);

        String payload = parts.length > 1 ? parts[1] : "";

        return new NetworkMessage(type,payload);
    }
}