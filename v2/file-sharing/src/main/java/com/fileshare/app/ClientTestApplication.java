package com.fileshare.app;

import com.fileshare.infrastructure.networking.client.ClientManager;
import com.fileshare.infrastructure.networking.protocol.MessageFactory;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;

public class ClientTestApplication {

    public static void main(String[] args)throws Exception {

        ClientManager client =new ClientManager("localhost",8080);

        client.connect();

        NetworkMessage response =client.send(MessageFactory.createRoom("Harsh","127.0.0.1"));

        System.out.println("Server Response: "+ response);

        client.disconnect();
    }
}