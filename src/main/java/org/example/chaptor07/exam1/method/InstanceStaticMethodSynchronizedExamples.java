package org.example.chaptor07.exam1.method;

public class InstanceStaticMethodSynchronizedExamples {

    private static int staticCount = 0;
    private int instanceCount = 0;

    public synchronized void incrementInstanceCount() {
        instanceCount++;
        // System.out.println(Thread.currentThread().getName() + "가 증가 시켰습니다. 현재 값: " + count);
    }

    public synchronized void decrementInstanceCount() {
        instanceCount--;
        // System.out.println(Thread.currentThread().getName() + "가 감소 시켰습니다. 현재 값: " + count);
    }

    public static synchronized void incrementStaticCount() {
        staticCount++;
        // System.out.println(Thread.currentThread().getName() + "가 증가 시켰습니다. 현재 값: " + count);
    }

    public static synchronized void decrementStaticCount() {
        staticCount--;
        // System.out.println(Thread.currentThread().getName() + "가 감소 시켰습니다. 현재 값: " + count);
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        InstanceStaticMethodSynchronizedExamples example = new InstanceStaticMethodSynchronizedExamples();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                example.incrementInstanceCount();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                example.decrementInstanceCount();
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                InstanceStaticMethodSynchronizedExamples.incrementStaticCount();
            }
        });

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 10_000_000; i++) {
                InstanceStaticMethodSynchronizedExamples.decrementStaticCount();
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

        System.out.println("최종 값(instanceCount) : " + example.instanceCount);
        System.out.println("최종 값(staticCount) : " + InstanceStaticMethodSynchronizedExamples.staticCount);

        System.out.println("소요시간 : " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
