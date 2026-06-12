package com.fileshare.infrastructure.networking.protocol;

public class MessageSerializer {

    public String serialize(NetworkMessage message) {

        return message.type().name()+ ";" + message.payload();
    }
}