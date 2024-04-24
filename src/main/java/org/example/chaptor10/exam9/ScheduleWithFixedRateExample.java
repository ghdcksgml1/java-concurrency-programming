package org.example.chaptor10.exam9;

import com.chanhui.DateDistance;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleWithFixedRateExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        LocalDateTime startTime = LocalDateTime.now();
        Runnable task = () -> {
            try {
                String uuid = UUID.randomUUID().toString();
                System.out.printf("[%s] Thread: %s, %s%n", uuid, Thread.currentThread().getName(), DateDistance.of(startTime));
                Thread.sleep(1000);
                System.out.printf("[%s] Thread: %s, %s%n", uuid, Thread.currentThread().getName(), DateDistance.of(startTime));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        scheduledExecutorService.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(task, 1, 1, TimeUnit.SECONDS);

        Thread.sleep(10000);
        scheduledExecutorService.shutdown();
    }
}
