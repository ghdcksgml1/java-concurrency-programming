package org.example.chaptor10.exam5;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class FutureCancelExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            try {
                System.out.println(LocalDateTime.now() + " : 비동기 작업 시작..");
                Thread.sleep(2000);
                System.out.println(LocalDateTime.now() + " : 비동기 작업 완료..");

            } catch (InterruptedException e) {
                System.out.println("InterruptedException 발생했음!!");
            }

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        Thread.sleep(1000);
//        future.cancel(true);
        future.cancel(false);

        if (!future.isCancelled()) {

            int result = 0;
            try {
                System.out.println("future = " + future.isDone());
                result = future.get();
                System.out.println("future = " + future.isDone());
                executorService.shutdown();
            } catch (CancellationException e) {
                System.out.println("cancel!!");
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("작업이 취소되었습니다.");
        }
    }
}
