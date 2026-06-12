package com.fileshare.infrastructure.networking.client;

import java.io.IOException;
import java.net.Socket;

import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public class ClientManager {

    private final String host;
    private final int port;

    private ClientConnection connection;

    public ClientManager(String host,int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException {

        Socket socket = new Socket(host, port);

        this.connection = new ClientConnection(socket);

        System.out.println("Connected to server.");
    }

    public NetworkMessage send( NetworkMessage request)throws IOException {
        connection.write(request);
        return connection.read();
    }

    public void disconnect() throws IOException {

        if (connection != null) {
            connection.close();
        }
    }
}