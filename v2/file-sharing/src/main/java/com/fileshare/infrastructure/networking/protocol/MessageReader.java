package com.fileshare.infrastructure.networking.protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReader {

    private final BufferedReader reader;
    private final MessageDeserializer deserializer;

    public MessageReader(Socket socket)throws IOException {

        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        this.deserializer = new MessageDeserializer();
    }

    public NetworkMessage read() throws IOException {

        String line = reader.readLine();

        if (line == null) {
            return null;
        }

        return deserializer.deserialize(line);
    }
}