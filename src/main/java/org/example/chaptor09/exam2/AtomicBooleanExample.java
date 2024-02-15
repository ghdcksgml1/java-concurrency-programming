package org.example.chaptor09.exam2;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {

    private static AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    System.out.println("스레드1이 바쁜 대기 중..." + flag.get());
                }
                System.out.println("스레드1이 임계영역 수행 중");
                flag.set(false);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                while (!flag.compareAndSet(false, true)) {
                    System.out.println("스레드2가 바쁜 대기 중..." + flag.get());
                }
                System.out.println("스레드2가 임계영역 수행 중");
                flag.set(false);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
