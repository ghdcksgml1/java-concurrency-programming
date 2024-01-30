package org.example.chaptor06.exam3;

public class MutualExclusionExample {

    private int count = 0;

    public synchronized void increment() {
        count++;
        System.out.println("스레드: " + Thread.currentThread().getName() + " 카운터 값: " + count);
    }
    public static void main(String[] args) throws InterruptedException {
        MutualExclusionExample example = new MutualExclusionExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                example.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500000; i++) {
                example.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
