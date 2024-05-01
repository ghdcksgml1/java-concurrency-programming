package org.example.chaptor10.exam11;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class PreStartThreadsExample {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(maxPoolSize);

        int taskNum = 9;

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

//        executor.prestartCoreThread();
        executor.prestartAllCoreThreads(); // corePoolSize 만큼 생성해둠.

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.printf("%s [%s]가 테스크 (%d)를 실행하고 있습니다.\n", LocalDateTime.now(), Thread.currentThread().getName(), taskId);
            });
        }
        executor.shutdown();
    }
}
