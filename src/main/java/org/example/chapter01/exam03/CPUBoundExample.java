package org.example.chapter01.exam03;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CPUBoundExample {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        long startTime2 = System.currentTimeMillis();
        ArrayList<Future<?>> futures = new ArrayList<>();

        for (int i=0; i<numThreads; i++) {
            Future<?> future = executorService.submit(() -> {
                // CPU 연산이 집중되고 오래 걸리는 작업
                long result = 0;
                for (long j=0; j<1000000000L; j++) {
                    result += j;
                }

                // 아주 잠깐 대기
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("스레드: " + Thread.currentThread().getName() + ", " + result); // CPU Bound 일때 ContextSwitching
            });
            futures.add(future);
        }
        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        long endTime2 = System.currentTimeMillis();
        System.out.println("CPU 개수를 초과하는 데이터를 병렬로 처리하는데 걸린 시간: " + (endTime2 - startTime2) + "ms");
        executorService.shutdown();
    }
}
