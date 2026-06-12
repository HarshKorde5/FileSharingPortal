package com.fileshare.infrastructure.networking.client;

import java.io.IOException;
import java.net.Socket;

import com.fileshare.infrastructure.networking.protocol.MessageReader;
import com.fileshare.infrastructure.networking.protocol.MessageWriter;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public class ClientConnection {

    private final Socket socket;
    private final MessageReader reader;
    private final MessageWriter writer;
    
    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new MessageReader(socket);
        this.writer = new MessageWriter(socket);
    }

    public Socket getSocket() {
        return socket;
    }

    public String getRemoteAddress() {
        return socket.getInetAddress().getHostAddress();
    }

    public void close() throws IOException {
        socket.close();
    }

    public NetworkMessage read()throws IOException {
        return reader.read();
    }

    public void write(NetworkMessage message) {

        writer.write(message);
    }
}