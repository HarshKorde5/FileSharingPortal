package com.fileshare.infrastructure.networking.server;

import java.util.concurrent.atomic.AtomicInteger;

public class TransferPortAllocator {

    private static final int START_PORT = 9000;

    private final AtomicInteger nextPort =
            new AtomicInteger(START_PORT);

    public int allocatePort() {
        return nextPort.getAndIncrement();
    }
}