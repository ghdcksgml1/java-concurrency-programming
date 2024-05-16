package org.example.chaptor11.exam7;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenComposeExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyService service = new MyService();
        CompletableFuture<Integer> cf1 = service.getData1(5); // cf1
        CompletableFuture<Integer> cf2 = cf1.thenCompose(result -> { // cf2
            System.out.println("Thread: " + Thread.currentThread().getName());
            return service.getData2(result); // cf3
        });

        Integer finalResult = cf2.get();
        System.out.println("finalResult = " + finalResult);
    }

    static class MyService {
        public CompletableFuture<Integer> getData1(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return input * 2;
            });
        }

        public CompletableFuture<Integer> getData2(int input) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return input * 10;
            });
        }
    }
}
