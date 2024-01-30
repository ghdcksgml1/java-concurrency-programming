package org.example.chaptor06.exam2;

public class CountingSemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        int permits = 10;
        CommonSemaphore semaphore = new CountingSemaphore(permits);
        SharedResource resource = new SharedResource(semaphore);

        int threadCount = 100;

        Thread[] threads = new Thread[threadCount];
        for (int i=0; i<threadCount; i++) {
            threads[i] = new Thread(() -> {
                synchronized (CountingSemaphoreExample.class) {
                    resource.sum();
                }
            });
            threads[i].start();
        }

        for (int i=0; i<threadCount; i++) {
            threads[i].join();
        }

        System.out.println("기대 값: " + (10000000 * threadCount));
        System.out.println("최종 값: " + resource.getSharedValue());
    }
}
