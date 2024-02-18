package org.example.chaptor09.exam3;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterCASExample {
    private static AtomicIntegerFieldUpdater<YourClass> fieldUpdater;

    static class YourClass {
        volatile int counter;
        public int getCounter() {
            return counter;
        }
    }

    private static int NUM_THREADS = 3;

    public static void main(String[] args) {
        fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(YourClass.class, "counter");
        YourClass yourClass = new YourClass();

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                int expectedValue = 0, newValue = 0;
                for (int j = 0; j < 100000; j++) {
                    do {
                        expectedValue = fieldUpdater.get(yourClass);
                        newValue = expectedValue + 1;
                    } while(!fieldUpdater.compareAndSet(yourClass, expectedValue, newValue));
                }
                System.out.println(Thread.currentThread().getName() + ": " + expectedValue + ", " + newValue);
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("expected value: " + 100000 * NUM_THREADS + ", value: " + fieldUpdater.get(yourClass));
        System.out.println("expected value: " + 100000 * NUM_THREADS + ", value: " + yourClass.getCounter());
    }
}
