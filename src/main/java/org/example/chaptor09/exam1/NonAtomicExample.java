package org.example.chaptor09.exam1;

public class NonAtomicExample {

    private static int value = 0;
    private static int NUM_THREADS = 3;

    public static void main(String[] args) {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                int expectedValue = 0, newValue = 0;
                for (int j = 0; j < 100000; j++) {
                    expectedValue = value;
                    newValue = expectedValue + 1;
                    value = newValue;
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

        System.out.println("value: " + value);
    }
}
