package com.fileshare.infrastructure.networking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fileshare.infrastructure.networking.client.ClientConnection;

public class ServerManager {

    private final int port;
    private final ExecutorService connectionPool;

    public ServerManager(int port) {

        this.port = port;

        this.connectionPool = Executors.newCachedThreadPool();
    }

    public void start() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server started on port "+ port);

            while (true) {

                Socket socket = serverSocket.accept();

                ClientConnection connection = new ClientConnection(socket);

                connectionPool.submit(new ConnectionHandler(connection));
            }
        }
    }
}