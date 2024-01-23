package org.example.chapter01.exam01;

import java.util.ArrayList;
import java.util.List;

public class ConcurrencyExample {
    public static void main(String[] args) {
//        int cpuCores = Runtime.getRuntime().availableProcessors();
        int cpuCores = Runtime.getRuntime().availableProcessors() + 1;
        System.out.println("cpuCores = " + cpuCores);

        // CPU 개수만큼 데이터를 생성
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        // CPU 개수만큼 데이터를 병렬로 처리
        long startTime1 = System.currentTimeMillis();

        long sum1 = data.parallelStream() // ForkJoinPool 사용
                .mapToLong(i -> {
                    try {
                        System.out.println("Thread.currentThread() = " + Thread.currentThread().getName());
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * i;
                })
                .sum();

        long endTime1 = System.currentTimeMillis();

        System.out.println("CPU 개수만큼 데이터를 병렬로 처리하는데 걸린 시간 : " + (endTime1 - startTime1) + "ms");
        System.out.println("결과1: " + sum1);
    }
}
