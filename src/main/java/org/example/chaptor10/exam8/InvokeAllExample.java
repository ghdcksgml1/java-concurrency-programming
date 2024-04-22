package org.example.chaptor10.exam8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Integer>> tasks = new ArrayList<>();

        tasks.add(() -> {
            Thread.sleep(3000);
            return 1;
        });

        tasks.add(() -> {
            Thread.sleep(2000);
            return 2;
        });

        tasks.add(() -> {
            Thread.sleep(1000);
            throw new RuntimeException("invokeAll error");
        });

        long startTime = System.currentTimeMillis();
        try {
            List<Future<Integer>> futures = executorService.invokeAll(tasks);
            for (Future<Integer> future : futures) {
                try {
                    Integer result = future.get();
                    System.out.println("result = " + result);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("endTime = " + (System.currentTimeMillis() - startTime) + " (ms)");
        }
        executorService.shutdown();
    }
}
