package com.fileshare.infrastructure.networking.protocol;

public final class MessageFactory {

    private MessageFactory() {}

    public static NetworkMessage createRoom(String username, String ipAddress) {

        return new NetworkMessage(MessageType.CREATE_ROOM,username+"|"+ipAddress);
    }

    public static NetworkMessage joinRoom(String roomCode, String username, String ipAddress) {

        return new NetworkMessage(MessageType.JOIN_ROOM,roomCode + "|" + username+"|"+ipAddress);
    }

    public static NetworkMessage heartbeat() {

        return new NetworkMessage(MessageType.HEARTBEAT,"");
    }

    public static NetworkMessage disconnect() {

        return new NetworkMessage(MessageType.DISCONNECT,"");
    }
}