package org.example.chaptor11.exam11;

import java.util.concurrent.CompletableFuture;

public class WaitingOfJoinExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            sleep(3000);
            System.out.println("비동기 시작");
            return 1;
        });

        CompletableFuture<Void> result = cf.thenApplyAsync(r -> {
            sleep(3000);
            System.out.println("비동기 실행 1");
            return r + 2;
        }).thenApplyAsync(r -> {
            sleep(3000);
            System.out.println("비동기 실행 2");
            return r + 3;
        }).thenAcceptAsync(r -> {
            sleep(3000);
            System.out.println("비동기 실행 3");
        });

        result.join();
        System.out.println("메인 스레드 종료");

    }

    private static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
