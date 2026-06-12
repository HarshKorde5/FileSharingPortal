package com.fileshare.infrastructure.networking.protocol;

public final class MessageFactory {

    private MessageFactory() {}

    public static NetworkMessage createRoom(String username) {

        return new NetworkMessage(MessageType.CREATE_ROOM,username);
    }

    public static NetworkMessage joinRoom(String roomCode, String username) {

        return new NetworkMessage(MessageType.JOIN_ROOM,roomCode + "|" + username);
    }

    public static NetworkMessage heartbeat() {

        return new NetworkMessage(MessageType.HEARTBEAT,"");
    }

    public static NetworkMessage disconnect() {

        return new NetworkMessage(MessageType.DISCONNECT,"");
    }
}