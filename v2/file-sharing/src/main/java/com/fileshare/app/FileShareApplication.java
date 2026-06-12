package com.fileshare.app;

import com.fileshare.application.services.InMemoryRoomService;
import com.fileshare.application.usecases.CreateRoomUseCase;
import com.fileshare.application.usecases.JoinRoomUseCase;
import com.fileshare.domain.interfaces.RoomService;
import com.fileshare.infrastructure.networking.handlers.DefaultMessageProcessor;
import com.fileshare.infrastructure.networking.handlers.MessageProcessor;
import com.fileshare.infrastructure.networking.server.ServerManager;

public class FileShareApplication {

    public static void main(String[] args) throws Exception {

        RoomService roomService = new InMemoryRoomService();

        CreateRoomUseCase createRoomUseCase = new CreateRoomUseCase(roomService);

        JoinRoomUseCase joinRoomUseCase = new JoinRoomUseCase(roomService);

        MessageProcessor processor = new DefaultMessageProcessor(createRoomUseCase, joinRoomUseCase);

        ServerManager serverManager = new ServerManager( 8080,processor);

        serverManager.start();
    }
}