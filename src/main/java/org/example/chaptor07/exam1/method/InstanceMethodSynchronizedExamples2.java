package org.example.chaptor07.exam1.method;

public class InstanceMethodSynchronizedExamples2 {

    private int count = 0;

    public synchronized void increment() {
        count++;
        // System.out.println(Thread.currentThread().getName() + "가 증가 시켰습니다. 현재 값: " + count);
    }

    public synchronized void decrement() {
        count--;
        // System.out.println(Thread.currentThread().getName() + "가 감소 시켰습니다. 현재 값: " + count);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        InstanceMethodSynchronizedExamples2 counter = new InstanceMethodSynchronizedExamples2();
        InstanceMethodSynchronizedExamples2 counter2 = new InstanceMethodSynchronizedExamples2();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                counter.increment();
                counter2.decrement();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                counter.decrement();
                counter2.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 값 1 : " + counter.getCount());
        System.out.println("최종 값 2 : " + counter2.getCount());

        System.out.println("소요시간 : " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
