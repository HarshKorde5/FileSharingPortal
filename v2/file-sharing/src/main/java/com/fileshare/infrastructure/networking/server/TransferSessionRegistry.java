package com.fileshare.infrastructure.networking.server;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TransferSessionRegistry {

    private final Map<UUID, TransferSessionContext> sessions =
            new ConcurrentHashMap<>();

    public void register(
            TransferSessionContext context) {

        sessions.put(
                context.getTransferId(),
                context);
    }

    public TransferSessionContext find(
            UUID transferId) {

        return sessions.get(
                transferId);
    }

    public void remove(
            UUID transferId) {

        sessions.remove(
                transferId);
    }
}