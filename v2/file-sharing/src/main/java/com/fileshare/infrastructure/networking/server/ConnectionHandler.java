package com.fileshare.infrastructure.networking.server;

import com.fileshare.infrastructure.networking.client.ClientConnection;
import com.fileshare.infrastructure.networking.handlers.MessageProcessor;
import com.fileshare.infrastructure.networking.protocol.MessageFactory;
import com.fileshare.infrastructure.networking.protocol.MessageType;
import com.fileshare.infrastructure.networking.protocol.JsonMapper;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;
import com.fileshare.infrastructure.networking.protocol.payload.JoinRoomSuccessPayload;

public class ConnectionHandler implements Runnable {

    private final ClientConnection clientConnection;
    private final MessageProcessor processor;
    private final ConnectionContext context;
    private final RoomRegistry roomRegistry;
    private final RoomBroadcaster broadcaster;

    public ConnectionHandler(ClientConnection clientConnection, MessageProcessor processor, RoomRegistry roomRegistry,RoomBroadcaster broadcaster) {
        this.clientConnection = clientConnection;
        this.processor = processor;
        this.roomRegistry = roomRegistry;
        this.broadcaster = broadcaster;

        this.context = new ConnectionContext(clientConnection);
    }

    private void handleConnectionState(NetworkMessage request,NetworkMessage response)throws Exception {
        if (response.type() != MessageType.JOIN_ROOM_RESPONSE) {
            return;
        }

        JoinRoomSuccessPayload payload = JsonMapper.getInstance().readValue(response.payload(),JoinRoomSuccessPayload.class);

        context.setRoomCode(payload.roomCode());

        context.setUsername(payload.username());

        roomRegistry.addClient(payload.roomCode(),context);

        broadcaster.broadcastExcept(payload.roomCode(),context,MessageFactory.peerJoined(payload.roomCode(),payload.username()));
    }

    @Override
    public void run() {

        try {

            while (true) {

                NetworkMessage request = clientConnection.read();

                if (request == null) {
                    break;
                }

                NetworkMessage response = processor.process(request);

                handleConnectionState(request, response);

                clientConnection.write(response);
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {
                clientConnection.close();
            } catch (Exception ignored) {
            }
        }
    }
}