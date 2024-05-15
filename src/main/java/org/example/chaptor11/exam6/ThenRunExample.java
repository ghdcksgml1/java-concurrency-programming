package org.example.chaptor11.exam6;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ThenRunExample {
    public static void main(String[] args) {
        ThenAcceptExample.MyService myService = new ThenAcceptExample.MyService();
        CompletableFuture<List<Integer>> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread1: " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return 40;
        }).thenApply(result -> {
            return myService.getData().stream().map(data -> data + result).toList();
        });

        CompletableFuture<Void> cf1 = cf.thenRunAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(LocalDateTime.now() + " RunAsync() Thread: " + Thread.currentThread().getName());
        });
        CompletableFuture<Void> cf2 = cf.thenAcceptAsync(result -> {
            System.out.println(LocalDateTime.now() + " AcceptAsync() Thread: " + Thread.currentThread().getName());
            result.forEach(System.out::println);
        });

        cf1.join();
        cf2.join();
    }

    static class MyService {
        public List<Integer> getData() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Arrays.asList(1, 2, 3);
        }
    }
}
