package com.fileshare.infrastructure.networking.transfer;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReceiver {

    private static final int BUFFER_SIZE = 8192;

    public TransferHeader receiveFile(
            Path outputPath,
            Socket socket)
            throws Exception {

        try (
                DataInputStream socketInput =
                        new DataInputStream(
                                socket.getInputStream());

                OutputStream fileStream =
                        Files.newOutputStream(outputPath);

                BufferedOutputStream fileOutput =
                        new BufferedOutputStream(fileStream)
        ) {

            String jsonHeader =
                    socketInput.readUTF();

            TransferHeader header =
                    HeaderSerializer.deserialize(
                            jsonHeader);

            byte[] buffer =
                    new byte[BUFFER_SIZE];

            long remainingBytes =
                    header.fileSize();

            while (remainingBytes > 0) {

                int bytesRead =
                        socketInput.read(
                                buffer,
                                0,
                                (int) Math.min(
                                        buffer.length,
                                        remainingBytes));

                if (bytesRead == -1) {
                    throw new IllegalStateException(
                            "Connection closed before transfer completed.");
                }

                fileOutput.write(
                        buffer,
                        0,
                        bytesRead);

                remainingBytes -= bytesRead;
            }

            fileOutput.flush();

            return header;
        }
    }
}