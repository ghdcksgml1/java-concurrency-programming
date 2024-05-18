package org.example.chaptor11.exam9;

import java.util.concurrent.CompletableFuture;

public class HandleExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 10;
        }).handle((r, e) -> {
            if (e != null) {
                System.out.println("비동기 예외 처리 1: " + e.getMessage());
                return -1;
            }
            return r;
        });

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
                throw new RuntimeException("error");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 20;
        }).handle((r, e) -> {
            if (e != null) {
                System.out.println("비동기 예외 처리 2: " + e.getMessage());
                return -1;
            }
            return r;
        });

        CompletableFuture<Integer> cf3 = cf1.thenCombine(cf2, (r1, r2) -> {
            if (r1 == -1 || r2 == -1) {
                return -1;
            }
            return r1 + r2;
        });

        System.out.println("result: " + cf3.join());
    }
}
