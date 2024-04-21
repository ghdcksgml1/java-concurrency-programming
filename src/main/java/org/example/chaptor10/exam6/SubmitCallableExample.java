package org.example.chaptor10.exam6;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SubmitCallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            System.out.println("비동기 작업 실행");
            return "Hello World";
        });

        String result = future.get();
        System.out.println("result = " + result);
        executorService.shutdown();
    }
}
