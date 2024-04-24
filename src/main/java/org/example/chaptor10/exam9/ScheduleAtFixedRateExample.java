package org.example.chaptor10.exam9;

import com.chanhui.DateDistance;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleAtFixedRateExample {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        LocalDateTime startTime = LocalDateTime.now();
        Runnable task = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("Thread: " + Thread.currentThread().getName() + ", " + DateDistance.of(startTime));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);
        Thread.sleep(5000);
        scheduledExecutorService.shutdown();
    }
}
