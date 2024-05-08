package org.example.chaptor11.exam2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<Integer> future1 = executorService.submit(new Service1());
        Future<Integer> future2 = executorService.submit(new Service2(future1));
        Future<Integer> future3 = executorService.submit(new Service3(future2));
        Future<Integer> future4 = executorService.submit(new Service4(future3));
        Future<Integer> future5 = executorService.submit(new Service5(future4));

        // 최종 결과를 얻기 위해 future5의 완료를 기다림
        int finalResult = future5.get();

        executorService.shutdown();
        System.out.println("최종 결과: " + finalResult);
    }
}
