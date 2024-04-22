package org.example.chaptor10.exam7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class AwaitTerminationExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
        });

        executorService.submit(() -> {
            while (true) {
                System.out.println("데몬 스레드 실행 중...");
                Thread.sleep(1000);
            }
        });

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("메인 스레드 종료!");
    }
}
