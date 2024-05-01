package org.example.chaptor10.exam11;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 0L; // corePoolSize를 제외한 나머지 스레드의 유휴시간
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(maxPoolSize);

        int taskNum = 9;
        // maxPoolSize가 4개이고, workQueue의 capacity는 4이므로, 테스크를 4까지처리할 수 있고, 대기 큐에는 4개의 테스크를 담을 수 있다.
        // taskNum은 9이기 때문에 1개의 테스크가 처리되지 못해서 RejectException이 발생한다.

        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        for (int i = 0; i < taskNum; i++) {
            final int taskId = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.printf("%s [%s]가 테스크 (%d)를 실행하고 있습니다.\n", LocalDateTime.now(), Thread.currentThread().getName(), taskId);
            });
        }
        executor.shutdown();
    }
}
