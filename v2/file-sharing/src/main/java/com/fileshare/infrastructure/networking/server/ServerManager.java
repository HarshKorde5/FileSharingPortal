package com.fileshare.infrastructure.networking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fileshare.infrastructure.networking.client.ClientConnection;
import com.fileshare.infrastructure.networking.handlers.MessageProcessor;

public class ServerManager {

    private final int port;
    private final MessageProcessor processor;
    private final ExecutorService connectionPool;
    private final RoomRegistry roomRegistry;
    private final RoomBroadcaster broadcaster;

    public ServerManager(int port, MessageProcessor processor) {

        this.port = port;
        this.processor = processor;
        this.connectionPool = Executors.newCachedThreadPool();
        this.roomRegistry = new RoomRegistry();
        this.broadcaster = new RoomBroadcaster(roomRegistry);
    }

    public void start() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server started on port "+ port);

            while (true) {

                Socket socket = serverSocket.accept();

                try{
                    ClientConnection connection = new ClientConnection(socket);

                    connectionPool.submit(new ConnectionHandler(connection, processor,roomRegistry, broadcaster));
                }catch(IOException e){
                    socket.close();
                }
            }
        }
    }
}