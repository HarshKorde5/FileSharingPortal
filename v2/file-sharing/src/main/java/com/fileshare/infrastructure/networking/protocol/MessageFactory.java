package com.fileshare.infrastructure.networking.protocol;

import com.fileshare.infrastructure.networking.protocol.payload.CreateRoomPayload;
import com.fileshare.infrastructure.networking.protocol.payload.FileTransferAcceptedPayload;
import com.fileshare.infrastructure.networking.protocol.payload.FileTransferRejectedPayload;
import com.fileshare.infrastructure.networking.protocol.payload.FileTransferRequestPayload;
import com.fileshare.infrastructure.networking.protocol.payload.JoinRoomPayload;
import com.fileshare.infrastructure.networking.protocol.payload.PeerJoinedPayload;
import com.fileshare.infrastructure.networking.protocol.payload.PeerLeftPayload;

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

    public static NetworkMessage peerJoined(String roomCode,String username) {
        try {
            String payload =JsonMapper.getInstance().writeValueAsString(new PeerJoinedPayload(roomCode,username));

            return new NetworkMessage(MessageType.PEER_JOINED_NOTIFICATION,payload);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static NetworkMessage peerLeft(String roomCode,String username) {
        try {
            String payload =JsonMapper.getInstance().writeValueAsString(new PeerLeftPayload(roomCode,username));

            return new NetworkMessage(MessageType.PEER_LEFT_NOTIFICATION,payload);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static NetworkMessage fileTransferRequest(FileTransferRequestPayload payload) {

        try {
            String json = JsonMapper.getInstance().writeValueAsString(payload);

            return new NetworkMessage(MessageType.FILE_TRANSFER_REQUEST,json);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static NetworkMessage fileTransferAccepted(FileTransferAcceptedPayload payload) {
        try {
            String json = JsonMapper.getInstance().writeValueAsString(payload);
            return new NetworkMessage(MessageType.FILE_TRANSFER_ACCEPTED,json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static NetworkMessage fileTransferRejected(FileTransferRejectedPayload payload) {
        try {
            String json = JsonMapper.getInstance().writeValueAsString(payload);
            return new NetworkMessage(MessageType.FILE_TRANSFER_REJECTED,json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}