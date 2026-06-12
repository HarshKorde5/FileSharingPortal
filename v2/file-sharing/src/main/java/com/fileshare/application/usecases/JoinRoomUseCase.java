package com.fileshare.application.usecases;

import java.util.UUID;

import com.fileshare.application.dto.JoinRoomRequest;
import com.fileshare.application.dto.JoinRoomResponse;
import com.fileshare.domain.enums.PeerRole;
import com.fileshare.domain.interfaces.RoomService;
import com.fileshare.domain.models.Peer;
import com.fileshare.domain.models.Room;

public class JoinRoomUseCase {

    private final RoomService roomService;

    public JoinRoomUseCase(RoomService roomService) {
        this.roomService = roomService;
    }

    public JoinRoomResponse execute(JoinRoomRequest request) {

        Room room = roomService.findByRoomCode(
                request.roomCode());

        if (room == null) {
            throw new IllegalArgumentException(
                    "Room not found.");
        }

        Peer participant = new Peer(
                UUID.randomUUID(),
                request.username(),
                request.ipAddress(),
                PeerRole.PARTICIPANT);

        room.addMember(participant);

        roomService.save(room);

        return new JoinRoomResponse(
                room.getRoomCode(),
                participant.getUsername(),
                participant.getPeerId(),
                room.memberCount());
    }
}