package org.example.chaptor08.exam2;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();

                try {
                    System.out.println("스레드 1이 락을 획득했습니다.");
                } finally {
                    lock.unlock();
                    System.out.println("스레드 1이 락을 해제했습니다.");
                }

            } catch (InterruptedException e) {
                System.out.println("스레드 1이 인터럽트를 받았습니다.");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();

                try {
                    System.out.println("스레드 2가 락을 획득했습니다.");
                } finally {
                    lock.unlock();
                    System.out.println("스레드 2가 락을 해제했습니다.");
                }

            } catch (InterruptedException e) {
                System.out.println("스레드 2가 인터럽트를 받았습니다.");
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(10); // 작업이 끝나고 Interrupt가 걸리게 되면, 의미가 없다.
        thread1.interrupt();

        thread1.join();
        thread2.join();
    }
}
