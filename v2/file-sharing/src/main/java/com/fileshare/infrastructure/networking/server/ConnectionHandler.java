package com.fileshare.infrastructure.networking.server;

import com.fileshare.infrastructure.networking.client.ClientConnection;
import com.fileshare.infrastructure.networking.handlers.MessageProcessor;
import com.fileshare.infrastructure.networking.protocol.JsonMapper;
import com.fileshare.infrastructure.networking.protocol.MessageFactory;
import com.fileshare.infrastructure.networking.protocol.MessageType;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;
import com.fileshare.infrastructure.networking.protocol.payload.CreateRoomSuccessPayload;
import com.fileshare.infrastructure.networking.protocol.payload.FileTransferRejectedPayload;
import com.fileshare.infrastructure.networking.protocol.payload.FileTransferRequestPayload;
import com.fileshare.infrastructure.networking.protocol.payload.JoinRoomSuccessPayload;

public class ConnectionHandler implements Runnable {

    private final ClientConnection clientConnection;
    private final MessageProcessor processor;

    private final RoomRegistry roomRegistry;
    private final RoomBroadcaster broadcaster;
    private final ClientRegistry clientRegistry;
    private final TransferRouter transferRouter;
    private final ConnectionContext context;

    public ConnectionHandler(ClientConnection clientConnection,MessageProcessor processor,RoomRegistry roomRegistry,RoomBroadcaster broadcaster,ClientRegistry clientRegistry,  TransferRouter transferRouter) {

        this.clientConnection = clientConnection;
        this.processor = processor;
        this.roomRegistry = roomRegistry;
        this.broadcaster = broadcaster;
        this.clientRegistry = clientRegistry;
        this.transferRouter = transferRouter;
        this.context = new ConnectionContext(clientConnection);
    }

    @Override
    public void run() {

        try {

            while (true) {

                NetworkMessage request = clientConnection.read();

                if (request == null) {
                    break;
                }
                if (request.type() == MessageType.FILE_TRANSFER_REQUEST) {

                    handleTransferRequest(request);

                    continue;
                }

                NetworkMessage response = processor.process(request);

                handleConnectionState(request,response);

                clientConnection.write(response);
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            cleanup();

            try {
                clientConnection.close();
            } catch (Exception ignored) {}
        }
    }

    private void handleConnectionState(NetworkMessage request,NetworkMessage response)throws Exception {

        if (response.type() == MessageType.CREATE_ROOM_RESPONSE) {

            CreateRoomSuccessPayload payload = JsonMapper.getInstance().readValue(response.payload(),CreateRoomSuccessPayload.class);

            context.setPeerId(payload.peerId());

            context.setUsername(payload.hostUsername());

            context.setRoomCode(payload.roomCode());

            roomRegistry.addClient(payload.roomCode(),context);

            clientRegistry.register(payload.peerId(),context);

            return;
        }

        if (response.type() == MessageType.JOIN_ROOM_RESPONSE) {

            JoinRoomSuccessPayload payload = JsonMapper.getInstance().readValue(response.payload(),JoinRoomSuccessPayload.class);

            context.setPeerId(payload.peerId());

            context.setUsername(payload.username());

            context.setRoomCode(payload.roomCode());

            roomRegistry.addClient(payload.roomCode(),context);

            clientRegistry.register(payload.peerId(),context);

            broadcaster.broadcastExcept(payload.roomCode(),context,MessageFactory.peerJoined(payload.roomCode(),payload.username()));
        }
    }

    private void cleanup() {

        if (context.getPeerId() != null) {

            clientRegistry.unregister(context.getPeerId());
        }

        if (context.getRoomCode() != null) {

            roomRegistry.removeClient(context.getRoomCode(),context);
        }
    }

    private void handleTransferRequest(NetworkMessage request)throws Exception {
        FileTransferRequestPayload payload = JsonMapper.getInstance().readValue(request.payload(),FileTransferRequestPayload.class);

        boolean delivered = transferRouter.routeToPeer(payload.receiverPeerId(),request);

        if (!delivered) {
            NetworkMessage rejected = MessageFactory.fileTransferRejected(new FileTransferRejectedPayload(payload.roomCode(),payload.senderPeerId(),payload.receiverPeerId(),payload.fileName(),"Receiver is offline"));

            clientConnection.write(rejected);
        }
    }
}