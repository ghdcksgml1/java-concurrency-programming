package org.example.chaptor08.exam4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockFairnessPerformanceExample {
    private static final int THREAD_COUNT = 4;
    private static final int ITERATIONS = 1_000;
    private static final Lock fairLock = new ReentrantLock(true);
    private static final Lock unfairLock = new ReentrantLock(false);
    public static void main(String[] args) throws InterruptedException {
        runTest("공정한 락", fairLock);
        runTest("비공정한 락", unfairLock);
    }

    private static void runTest(String lockType, Lock lock) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    lock.lock();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long runningTime = System.currentTimeMillis() - startTime;
        System.out.println(lockType + "의 실행시간: " + runningTime + "ms");
    }
}
