package org.example.chaptor10.exam11;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolStateExample {
    public static void main(String[] args) throws InterruptedException {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L;
        int workQueueCapacity = 10;

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(workQueueCapacity)
        );

        // 작업 제출
        for (int i = 0; i < 1; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " 가 테스크 " + taskId + " 를 실행하고 있습니다.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return 42;
            });
        }

        // 스레드 풀 종료
        executor.shutdown();
        System.out.println("===================================");

        printThreadPoolState(executor);

        System.out.println("===================================");

        // 스레드 풀이 종료될때까지 대기
        boolean terminated = executor.awaitTermination(1, TimeUnit.SECONDS);
        if (!terminated) {
            executor.shutdownNow();
        }

        System.out.println("===================================");

        while(!executor.isTerminated()) {
            System.out.println("스레드 풀 종료 중..");
        }
        printThreadPoolState(executor);

        System.out.println("===================================");
    }

    private static void printThreadPoolState(ThreadPoolExecutor executor) {
        if (executor.getActiveCount() > 0) {
            System.out.println("ThreadPoolExecutor is RUNNING");
        }
        if (executor.isShutdown()) {
            System.out.println("ThreadPoolExecutor is SHUTDOWN or STOP");
        }
        if (executor.isTerminating()) {
            System.out.println("ThreadPoolExecutor is TIDYING");
        }
        if (executor.isTerminated()) {
            System.out.println("ThreadPoolExecutor is TERMINATED");
        }
    }
}
