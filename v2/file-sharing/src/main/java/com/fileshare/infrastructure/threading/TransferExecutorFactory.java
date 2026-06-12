package com.fileshare.infrastructure.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class TransferExecutorFactory {

    private TransferExecutorFactory() {
    }

    public static ExecutorService create() {

        return Executors.newCachedThreadPool();
    }
}