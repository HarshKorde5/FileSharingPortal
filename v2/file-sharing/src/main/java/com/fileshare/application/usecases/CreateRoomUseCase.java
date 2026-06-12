package com.fileshare.application.usecases;

import com.fileshare.application.dto.CreateRoomRequest;
import com.fileshare.application.dto.CreateRoomResponse;
import com.fileshare.domain.interfaces.RoomService;
import com.fileshare.domain.models.Room;

public class CreateRoomUseCase {

    private final RoomService roomService;

    public CreateRoomUseCase(RoomService roomService) {
        this.roomService = roomService;
    }

    public CreateRoomResponse execute(CreateRoomRequest request) {

        Room room = roomService.createRoom(request.username(),request.ipAddress());

        return new CreateRoomResponse(room.getRoomCode(),room.getHost().getUsername(), room.getHost().getPeerId());
    }
}