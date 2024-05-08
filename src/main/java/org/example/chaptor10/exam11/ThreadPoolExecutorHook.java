package org.example.chaptor10.exam11;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorHook extends ThreadPoolExecutor {

    public ThreadPoolExecutorHook(int corePoolSize, int maxPoolSize, long keepAliveTime, TimeUnit timeUnit, BlockingQueue<Runnable> queue) {
        super(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, queue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println(Thread.currentThread().getName() + ": " + t.getName() + "가 작업을 실행하려고 합니다.");
        super.beforeExecute(t, r);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (t != null) {
            System.out.println(Thread.currentThread().getName() + ": " + "작업이 " + t.getMessage() + "예외가 발생했습니다.");
        } else {
            System.out.println(Thread.currentThread().getName() + ": " + "작업을 성공적으로 완료했습니다.");
        }
        super.afterExecute(r, t);
    }

    @Override
    protected void terminated() {
        System.out.println(Thread.currentThread().getName() + ": " + "스레드 풀 종료");
        super.terminated();
    }

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 2;
        long keepAliveTime = 0;
        int workQueueCapacity = 2;

        ThreadPoolExecutor executor = new ThreadPoolExecutorHook(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(workQueueCapacity)
        );

        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ": 가 테스트 " + taskId + " 를 실행하고 있습니다.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }
}
