package org.example.chapter01.exam03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOBoundExample {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i=0; i<numThreads; i++) {
            executorService.submit(() -> {
                try {
                    // IO가 집중되는 작업
                    for (int j=0; j<5; j++) {
                        Thread.sleep(2000);
                        System.out.println("스레드: " + Thread.currentThread().getName() + ", " + j); // IO Bound일때 ContextSwitching
                    }

                    // 아주 빠른 CPU 연산
                    int result = 0;
                    for (int j=0; j<10; j++) {
                        result += j;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executorService.shutdown();
    }
}
