package org.example.chaptor10.exam10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool_60_Example {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executorService.submit(() -> {
                System.out.println("Task  " + taskId + " is executing on " + Thread.currentThread().getName());
            });
        }

        // 60초 동안 아무작업도 수행하지 않음
        try {
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 60초 이상 동안 사용 되지 않은 스레드는 자동 종료됨.

        executorService.shutdown();
    }
}
