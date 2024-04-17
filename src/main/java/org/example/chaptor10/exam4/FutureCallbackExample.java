package org.example.chaptor10.exam4;

import java.util.concurrent.*;

public class FutureCallbackExample {
    interface Callback {
        void onComplete(int result);
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("Callable 작업 수행중..");
            Thread.sleep(2000);
            System.out.println("Callable 작업 완료");

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        System.out.println("비동기 작업 시작");

        registerCallback(future, result -> System.out.println("비동기 작업 결과: " + result));
        executorService.shutdown();
    }

    private static void registerCallback(Future<Integer> future, Callback callback) {
        new Thread(() -> {
            try {
                Integer result = future.get();
                callback.onComplete(result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
