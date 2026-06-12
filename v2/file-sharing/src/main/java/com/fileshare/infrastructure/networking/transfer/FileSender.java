package com.fileshare.infrastructure.networking.transfer;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSender {

    private static final int BUFFER_SIZE = 8192;

    public void sendFile(
            TransferHeader header,
            Path filePath,
            Socket socket)
            throws Exception {

        try (
                InputStream fileStream =
                        Files.newInputStream(filePath);

                BufferedInputStream fileInput =
                        new BufferedInputStream(fileStream);

                DataOutputStream socketOutput =
                        new DataOutputStream(
                                socket.getOutputStream())
        ) {

            String jsonHeader =
                    HeaderSerializer.serialize(header);

            socketOutput.writeUTF(jsonHeader);

            byte[] buffer =
                    new byte[BUFFER_SIZE];

            int bytesRead;

            while ((bytesRead =
                    fileInput.read(buffer))
                    != -1) {

                socketOutput.write(
                        buffer,
                        0,
                        bytesRead);
            }

            socketOutput.flush();
        }
    }
}