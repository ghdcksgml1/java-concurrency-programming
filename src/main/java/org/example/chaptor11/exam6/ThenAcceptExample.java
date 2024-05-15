package org.example.chaptor11.exam6;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ThenAcceptExample {
    public static void main(String[] args) {
        MyService myService = new MyService();
        CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread1: " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return 40;
        }).thenAccept(r -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println("결과: " + r);
            List<Integer> data = myService.getData();
            data.forEach(System.out::println);
        }).thenAcceptAsync(r -> {
            assert r == null;
            System.out.println("Thread: " + Thread.currentThread().getName());
            System.out.println("결과: " + r);
            List<Integer> data = myService.getData();
            data.forEach(System.out::println);
        }).join();
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
