package org.example.chaptor11.exam2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Integer result = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + ": Service1 시작");
            return 1;
        }).thenApplyAsync(result2 -> {
            System.out.println(Thread.currentThread().getName() + ": Service2 시작");
            return result2 + 1;
        }).thenApplyAsync(result3 -> {
            System.out.println(Thread.currentThread().getName() + ": Service3 시작");
            return result3 + 1;
        }).thenApplyAsync(result4 -> {
            System.out.println(Thread.currentThread().getName() + ": Service4 시작");
            return result4 + 1;
        }).thenApplyAsync(result5 -> {
            System.out.println(Thread.currentThread().getName() + ": Service5 시작");
            return result5 + 1;
        }).get();
        System.out.println("result = " + result);
    }
}
