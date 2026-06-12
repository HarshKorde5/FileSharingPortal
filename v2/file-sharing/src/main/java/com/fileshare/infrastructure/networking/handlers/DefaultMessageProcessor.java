package com.fileshare.infrastructure.networking.handlers;

import com.fileshare.application.dto.CreateRoomRequest;
import com.fileshare.application.dto.CreateRoomResponse;
import com.fileshare.application.dto.JoinRoomRequest;
import com.fileshare.application.dto.JoinRoomResponse;
import com.fileshare.application.usecases.CreateRoomUseCase;
import com.fileshare.application.usecases.JoinRoomUseCase;
import com.fileshare.infrastructure.networking.protocol.JsonMapper;
import com.fileshare.infrastructure.networking.protocol.MessageType;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;
import com.fileshare.infrastructure.networking.protocol.payload.CreateRoomPayload;
import com.fileshare.infrastructure.networking.protocol.payload.JoinRoomPayload;

public class DefaultMessageProcessor implements MessageProcessor {

    private final CreateRoomUseCase createRoomUseCase;
    private final JoinRoomUseCase joinRoomUseCase;

    public DefaultMessageProcessor(CreateRoomUseCase createRoomUseCase,JoinRoomUseCase joinRoomUseCase) {
        this.createRoomUseCase = createRoomUseCase;
        this.joinRoomUseCase = joinRoomUseCase;
    }

    @Override
    public NetworkMessage process(NetworkMessage message) {
        try {
            return switch (message.type()) {
                case CREATE_ROOM_REQUEST -> handleCreateRoom(message);
                case JOIN_ROOM_REQUEST -> handleJoinRoom(message);
                default -> new NetworkMessage(MessageType.DISCONNECT,"Unsupported message type");
            };
        } catch (Exception e) {
            return new NetworkMessage(MessageType.DISCONNECT,"Error: " + e.getMessage());
        }
    }

    private NetworkMessage handleCreateRoom(NetworkMessage message) throws Exception {

        CreateRoomPayload payload = JsonMapper.getInstance().readValue(message.payload(),CreateRoomPayload.class);

        CreateRoomRequest request = new CreateRoomRequest(payload.username(),payload.ipAddress());

        CreateRoomResponse response =createRoomUseCase.execute(request);

        return new NetworkMessage(MessageType.CREATE_ROOM_RESPONSE,response.roomCode());
    }

    private NetworkMessage handleJoinRoom(NetworkMessage message) throws Exception {

        JoinRoomPayload payload = JsonMapper.getInstance().readValue(message.payload(),JoinRoomPayload.class);

        JoinRoomRequest request = new JoinRoomRequest(payload.roomCode(),payload.username(),payload.ipAddress());

        JoinRoomResponse response = joinRoomUseCase.execute(request);

        return new NetworkMessage(MessageType.JOIN_ROOM_RESPONSE,response.roomCode());
    }
}