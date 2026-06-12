package com.fileshare.infrastructure.networking.transfer;

import java.io.IOException;
import java.net.Socket;

public class TransferClient {

    public Socket connect(
            String host,
            int port)
            throws IOException {

        return new Socket(
                host,
                port);
    }
}