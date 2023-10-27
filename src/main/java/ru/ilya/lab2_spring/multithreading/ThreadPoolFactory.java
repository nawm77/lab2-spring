package ru.ilya.lab2_spring.multithreading;

import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolFactory {
    @Value("thread-pool-size:4")
    private static final int POOL_SIZE = 4;
    private static final ExecutorService DEFAULT_POOL = Executors.newFixedThreadPool(POOL_SIZE);
    public static ExecutorService getDefaultPool() {
        return DEFAULT_POOL;
    }
}
