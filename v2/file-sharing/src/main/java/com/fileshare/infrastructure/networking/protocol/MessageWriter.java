package com.fileshare.infrastructure.networking.protocol;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageWriter {

    private final PrintWriter writer;
    private final MessageSerializer serializer;

    public MessageWriter(Socket socket)throws IOException {

        this.writer = new PrintWriter(socket.getOutputStream(),true);

        this.serializer = new MessageSerializer();
    }

    public void write(NetworkMessage message) {

        writer.println(serializer.serialize(message));
    }
}