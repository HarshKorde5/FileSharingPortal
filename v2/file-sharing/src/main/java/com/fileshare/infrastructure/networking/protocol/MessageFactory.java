package com.fileshare.infrastructure.networking.protocol;

import com.fileshare.infrastructure.networking.protocol.payload.CreateRoomPayload;
import com.fileshare.infrastructure.networking.protocol.payload.JoinRoomPayload;

public final class MessageFactory {

    private MessageFactory() {}

    
    public static NetworkMessage createRoom(String username,String ipAddress) {
        try {

            String payload = JsonMapper.getInstance().writeValueAsString(new CreateRoomPayload(username,ipAddress));

            return new NetworkMessage(MessageType.CREATE_ROOM_REQUEST,payload);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static NetworkMessage joinRoom(String roomCode, String username, String ipAddress) {

        try{
            String payload = JsonMapper.getInstance().writeValueAsString(new JoinRoomPayload(roomCode, username, ipAddress));

            return new NetworkMessage(MessageType.JOIN_ROOM_REQUEST, payload);            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static NetworkMessage heartbeat() {

        return new NetworkMessage(MessageType.HEARTBEAT,"");
    }

    public static NetworkMessage disconnect() {

        return new NetworkMessage(MessageType.DISCONNECT,"");
    }
}