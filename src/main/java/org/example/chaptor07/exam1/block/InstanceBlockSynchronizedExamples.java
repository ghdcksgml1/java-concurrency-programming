package org.example.chaptor07.exam1.block;

import org.example.chaptor07.exam1.method.InstanceStaticMethodSynchronizedExamples;

public class InstanceBlockSynchronizedExamples {
    private int count = 0;
    private Object lockObj = new Object();

    /**
     * 둘의 모니터가 다르기 때문에 동기화가 이루어지지 않는다.
     * 예상 : 40만, 실제 : 39만
     */

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
        InstanceBlockSynchronizedExamples example = new InstanceBlockSynchronizedExamples();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example.incrementBlockThis();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example.incrementBlockThis();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example.incrementBlockLockObject();
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example.incrementBlockLockObject();
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

        System.out.println("최종 값(instanceCount) : " + example.count);

        System.out.println("소요시간 : " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
