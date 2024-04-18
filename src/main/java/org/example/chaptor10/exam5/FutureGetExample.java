package org.example.chaptor10.exam5;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class FutureGetExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println(LocalDateTime.now() + " : 비동기 작업 시작..");
            Thread.sleep(2000);
            System.out.println(LocalDateTime.now() + " : 비동기 작업 완료..");

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        while (!future.isDone()) {
            System.out.println("작업을 기다리는 중..");
            Thread.sleep(500);
        }

        int result;
        try {
            System.out.println("future = " + future.isDone());
            result = future.get();
            System.out.println("future = " + future.isDone());
            executorService.shutdown();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        System.out.println("result = " + result);
    }
}
