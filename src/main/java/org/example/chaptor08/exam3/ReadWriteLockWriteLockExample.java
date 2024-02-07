package org.example.chaptor08.exam3;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockWriteLockExample {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        BankAccount account = new BankAccount(lock);

        // 읽기 스레드가 잔액을 조회
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int balance = account.getBalance();
                System.out.println(Thread.currentThread().getName() + " - 현재 잔액: " + balance);
            }).start();
        }

        // 쓰기 스레드가 입금
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int depositAmount = new Random().nextInt(10) * 1000;
                account.deposit(depositAmount);
                System.out.println(Thread.currentThread().getName() + " - 입금: " + depositAmount);
            }).start();
        }

        // 읽기 스레드가 잔액을 조회
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int balance = account.getBalance();
                System.out.println(Thread.currentThread().getName() + " - 현재 잔액: " + balance);
            }).start();
        }
    }
}
/*
Thread-1 - 현재 잔액: 0 - 동시 접근
Thread-0 - 현재 잔액: 0 - 동시 접근
Thread-3 - 입금: 4000 - 2초 대기
Thread-4 - 입금: 1000 - 2초 대기 (누적 4초 대기)
Thread-6 - 입금: 2000 - 2초 대기 (누적 6초 대기)
Thread-5 - 입금: 9000 - 2초 대기 (누적 8초 대기)
Thread-11 - 입금: 5000 - 2초 대기 (누적 10초 대기)
Thread-2 - 입금: 2000 - 2초 대기 (누적 12초 대기)
Thread-7 - 입금: 2000 - 2초 대기 (누적 14초 대기)
Thread-9 - 입금: 6000 - 2초 대기 (누적 16초 대기)
Thread-10 - 입금: 5000 - 2초 대기 (누적 18초 대기)
Thread-8 - 입금: 9000 - 2초 대기 (누적 20초 대기)
Thread-12 - 현재 잔액: 45000 - 동시 접근 (누적 20초 대기)
Thread-13 - 현재 잔액: 45000 - 동시 접근 (누적 20초 대기)
 */
