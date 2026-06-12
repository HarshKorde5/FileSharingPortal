package com.fileshare.app;

import com.fileshare.infrastructure.networking.server.ServerManager;

public class FileShareApplication {

    public static void main(String[] args)throws Exception {

        ServerManager serverManager = new ServerManager(8080);

        serverManager.start();
    }
}