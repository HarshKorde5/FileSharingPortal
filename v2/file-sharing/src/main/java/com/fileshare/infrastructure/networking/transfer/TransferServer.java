package com.fileshare.infrastructure.networking.transfer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TransferServer
        implements AutoCloseable {

    private final ServerSocket serverSocket;

    public TransferServer(int port)
            throws IOException {

        this.serverSocket =
                new ServerSocket(port);
    }

    public Socket accept()
            throws IOException {

        return serverSocket.accept();
    }

    @Override
    public void close()
            throws IOException {

        serverSocket.close();
    }
}