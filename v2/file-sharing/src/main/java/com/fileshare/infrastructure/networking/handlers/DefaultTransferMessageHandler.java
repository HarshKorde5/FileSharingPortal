package com.fileshare.infrastructure.networking.handlers;

import com.fileshare.infrastructure.networking.protocol.JsonMapper;
import com.fileshare.infrastructure.networking.protocol.NetworkMessage;
import com.fileshare.infrastructure.networking.protocol.payload.FileTransferAcceptedPayload;
import com.fileshare.infrastructure.networking.protocol.payload.FileTransferRejectedPayload;
import com.fileshare.infrastructure.networking.protocol.payload.FileTransferRequestPayload;
import com.fileshare.infrastructure.networking.server.TransferRouter;

public class DefaultTransferMessageHandler
        implements TransferMessageHandler {

    private final TransferRouter transferRouter;

    public DefaultTransferMessageHandler(
            TransferRouter transferRouter) {

        this.transferRouter = transferRouter;
    }

    @Override
    public void handle(NetworkMessage message) {

        try {

            switch (message.type()) {

                case FILE_TRANSFER_REQUEST ->
                        handleRequest(message);

                case FILE_TRANSFER_ACCEPTED ->
                        handleAccepted(message);

                case FILE_TRANSFER_REJECTED ->
                        handleRejected(message);

                default -> {
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void handleRequest(
            NetworkMessage message)
            throws Exception {

        FileTransferRequestPayload payload =
                JsonMapper.getInstance()
                        .readValue(
                                message.payload(),
                                FileTransferRequestPayload.class);

        transferRouter.routeToPeer(
                payload.receiverPeerId(),
                message);
    }

    private void handleAccepted(
            NetworkMessage message)
            throws Exception {

        FileTransferAcceptedPayload payload =
                JsonMapper.getInstance()
                        .readValue(
                                message.payload(),
                                FileTransferAcceptedPayload.class);

        transferRouter.routeToPeer(
                payload.senderPeerId(),
                message);
    }

    private void handleRejected(
            NetworkMessage message)
            throws Exception {

        FileTransferRejectedPayload payload =
                JsonMapper.getInstance()
                        .readValue(
                                message.payload(),
                                FileTransferRejectedPayload.class);

        transferRouter.routeToPeer(
                payload.senderPeerId(),
                message);
    }
}