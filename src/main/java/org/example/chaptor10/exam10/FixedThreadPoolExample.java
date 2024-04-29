package org.example.chaptor10.exam10;

import com.chanhui.RunningTimeFormatter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread: " + Thread.currentThread().getName());
            });
        }
        executorService.shutdown();
        System.out.println("완료 : " + RunningTimeFormatter.format(System.currentTimeMillis() - startTime));
    }
}
