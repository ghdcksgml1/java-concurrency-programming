package org.example.chaptor07.exam1.block;

class MethodBlock {}

public class StaticBlockSynchronizedExamples {
    private int count = 0;

    /**
     * 모니터를 공유하고 있지 않기 때문에, 동기화가 이루어지지 않는다.
     */
    public void incrementBlockClass() {
        synchronized (StaticBlockSynchronizedExamples.class) {
            count++;
            System.out.println(Thread.currentThread().getName() + "가 StaticBlockSynchronizedExamples에 의해 블록 동기화 함: " + count);
        }
    }

    public void incrementBlockOtherClass() {
        synchronized (MethodBlock.class) {
            count++;
            System.out.println(Thread.currentThread().getName() + "가 MethodBlock에 의해 블록 동기화 함: " + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        StaticBlockSynchronizedExamples example1 = new StaticBlockSynchronizedExamples();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example1.incrementBlockClass();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example1.incrementBlockOtherClass();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("최종 값(instanceCount) : " + example1.count);

        System.out.println("소요시간 : " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
