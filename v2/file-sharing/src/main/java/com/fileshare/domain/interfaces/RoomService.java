package com.fileshare.domain.interfaces;

import com.fileshare.domain.models.Room;

public interface RoomService {

    Room createRoom(String username, String ipAddress);
}