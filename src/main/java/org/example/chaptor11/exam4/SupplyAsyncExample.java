package org.example.chaptor11.exam4;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class SupplyAsyncExample {
    public static void main(String[] args) {
        MyService myService = new MyService();

        System.out.println("=============completable future=============");

        CompletableFuture<List<Integer>> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] 가 비동기 작업을 시작합니다.");
            return myService.getData();
        });

        List<Integer> result = cf.join();
        result.forEach(System.out::println);

        System.out.println("=============future=============");

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<List<Integer>> future = executorService.submit(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] 가 비동기 작업을 시작합니다.");
            return myService.getData();
        });

        try {
            List<Integer> integers = future.get();
            integers.forEach(System.out::println);
            executorService.shutdown();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("메인 스레드 종료");

    }
}

class MyService {
    public List<Integer> getData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(1, 2, 3);
    }
}
