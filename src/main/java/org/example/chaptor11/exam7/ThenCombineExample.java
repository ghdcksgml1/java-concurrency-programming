package org.example.chaptor11.exam7;

import com.chanhui.RunningTimeFormatter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCombineExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        MyService myService = new MyService();

        CompletableFuture<String> cf1 = myService.getData1();
        CompletableFuture<String> cf2 = myService.getData2();

        CompletableFuture<String> cf3 = cf1.thenCombine(cf2, (result1, result2) -> result1 + result2);
        CompletableFuture<String> cf4 = cf3.thenCompose(result -> CompletableFuture.supplyAsync(() -> result + " Java"));
        String cf3Result = cf3.join();
        System.out.println(RunningTimeFormatter.format(System.currentTimeMillis() - startTime) + " : " + cf3Result);
        System.out.println(RunningTimeFormatter.format(System.currentTimeMillis() - startTime) + " : " + cf4.join());
    }

    static class MyService {
        public CompletableFuture<String> getData1() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "Hello ";
            });
        }

        public CompletableFuture<String> getData2() {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return "World!";
            });
        }
    }
}
