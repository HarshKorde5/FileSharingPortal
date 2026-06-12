package com.fileshare.infrastructure.networking.handlers;

import com.fileshare.application.dto.CreateRoomRequest;
import com.fileshare.application.dto.CreateRoomResponse;
import com.fileshare.application.dto.JoinRoomRequest;
import com.fileshare.application.dto.JoinRoomResponse;
import com.fileshare.application.usecases.CreateRoomUseCase;
import com.fileshare.application.usecases.JoinRoomUseCase;
import com.fileshare.infrastructure.networking.protocol.MessageType;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public class DefaultMessageProcessor implements MessageProcessor {

    private final CreateRoomUseCase createRoomUseCase;
    private final JoinRoomUseCase joinRoomUseCase;

    public DefaultMessageProcessor(CreateRoomUseCase createRoomUseCase,JoinRoomUseCase joinRoomUseCase) {
        this.createRoomUseCase = createRoomUseCase;
        this.joinRoomUseCase = joinRoomUseCase;
    }

    @Override
    public NetworkMessage process(NetworkMessage message) {

        return switch (message.type()) {

            case CREATE_ROOM -> handleCreateRoom(message);

            case JOIN_ROOM -> handleJoinRoom(message);

            default -> new NetworkMessage(MessageType.DISCONNECT,"Unsupported message");
        };
    }

    private NetworkMessage handleCreateRoom(NetworkMessage message) {

        String[] parts = message.payload().split("\\|");

        CreateRoomRequest request = new CreateRoomRequest(parts[0],parts[1]);

        CreateRoomResponse response = createRoomUseCase.execute(request);

        return new NetworkMessage( MessageType.CREATE_ROOM, response.roomCode());
    }

    private NetworkMessage handleJoinRoom( NetworkMessage message) {

        String[] parts = message.payload().split("\\|");

        JoinRoomRequest request = new JoinRoomRequest(parts[0],parts[1],parts[2]);

        JoinRoomResponse response = joinRoomUseCase.execute(request);

        return new NetworkMessage(MessageType.JOIN_ROOM,response.roomCode());
    }
}