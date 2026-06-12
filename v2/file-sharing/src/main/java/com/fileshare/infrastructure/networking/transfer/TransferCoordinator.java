package com.fileshare.infrastructure.networking.transfer;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;

public class TransferCoordinator {

    private final ExecutorService executorService;

    public TransferCoordinator(
            ExecutorService executorService) {

        this.executorService = executorService;
    }

    public void startReceiver(
            int port,
            Path outputPath) {

        executorService.submit(() -> {

            try (TransferServer transferServer =
                         new TransferServer(port)) {

                Socket socket =
                        transferServer.accept();

                FileReceiver receiver =
                        new FileReceiver();

                TransferHeader header =
                        receiver.receiveFile(
                                outputPath,
                                socket);

                System.out.println(
                        "Transfer completed: "
                                + header.fileName());

            } catch (IOException e) {

                e.printStackTrace();

            } catch (Exception e) {

                throw new RuntimeException(e);
            }
        });
    }

    public void startSender(
            String host,
            int port,
            Path filePath,
            TransferHeader header) {

        executorService.submit(() -> {

            try {

                TransferClient client =
                        new TransferClient();

                Socket socket =
                        client.connect(
                                host,
                                port);

                FileSender sender =
                        new FileSender();

                sender.sendFile(
                        header,
                        filePath,
                        socket);

                socket.close();

                System.out.println(
                        "Transfer completed: "
                                + header.fileName());

            } catch (Exception e) {

                e.printStackTrace();
            }
        });
    }
}