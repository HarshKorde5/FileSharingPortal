package com.fileshare.app;

import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import com.fileshare.infrastructure.networking.transfer.TransferCoordinator;
import com.fileshare.infrastructure.networking.transfer.TransferHeader;
import com.fileshare.infrastructure.threading.TransferExecutorFactory;

public class TransferTestApplication {

    public static void main(String[] args)
            throws Exception {

        ExecutorService executorService =
                TransferExecutorFactory.create();

        TransferCoordinator coordinator =
                new TransferCoordinator(
                        executorService);

        coordinator.startReceiver(
                9000,
                Path.of("received.txt"));

        Thread.sleep(2000);

        coordinator.startSender(
                "localhost",
                9000,
                Path.of("sample.txt"),
                new TransferHeader(
                        UUID.randomUUID(),
                        "sample.txt",
                        Path.of("sample.txt")
                                .toFile()
                                .length())
        );
    }
}