package org.example.chaptor07.exam1.block;

public class InstanceBlockSynchronizedExamples2 {
    private int count = 0;
    private Object lockObj = new Object();

    public void incrementBlockThis() {
        synchronized (this) {
            count++;
            System.out.println(Thread.currentThread().getName() + "가 this에 의해 블록 동기화 함: " + count);
        }
    }

    public void incrementBlockLockObject() {
        synchronized (lockObj) {
            count++;
            System.out.println(Thread.currentThread().getName() + "가 lockObj에 의해 블록 동기화 함: " + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        InstanceBlockSynchronizedExamples2 example1 = new InstanceBlockSynchronizedExamples2();
        InstanceBlockSynchronizedExamples2 example2 = new InstanceBlockSynchronizedExamples2();

        /**
         * 각기 다른 모니터를 가지고 있어서 동기화가 이루어짐.
         */
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example1.incrementBlockThis();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example2.incrementBlockThis();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example1.incrementBlockLockObject();
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example2.incrementBlockLockObject();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println("최종 값(instanceCount) : " + example1.count);
        System.out.println("최종 값(instanceCount) : " + example2.count);

        System.out.println("소요시간 : " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
