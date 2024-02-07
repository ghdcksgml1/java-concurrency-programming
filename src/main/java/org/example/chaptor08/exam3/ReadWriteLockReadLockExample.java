package org.example.chaptor08.exam3;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockReadLockExample {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        BankAccount account = new BankAccount(lock);

        // 읽기 스레드가 잔액을 조회
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                int balance = account.getBalance();
                System.out.println(Thread.currentThread().getName() + " - 현재 잔액: " + balance);
            }).start();
        }

        // 쓰기 스레드가 입금
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int depositAmount = new Random().nextInt(10) * 1000;
                account.deposit(depositAmount);
                System.out.println(Thread.currentThread().getName() + " - 입금: " + depositAmount);
            }).start();
        }

        // 읽기 스레드가 잔액을 조회
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int balance = account.getBalance();
                System.out.println(Thread.currentThread().getName() + " - 현재 잔액: " + balance);
            }).start();
        }
    }
}
/*
Thread-7 - 현재 잔액: 0 - 동시 접근
Thread-0 - 현재 잔액: 0 - 동시 접근
Thread-1 - 현재 잔액: 0 - 동시 접근
Thread-2 - 현재 잔액: 0 - 동시 접근
Thread-6 - 현재 잔액: 0 - 동시 접근
Thread-3 - 현재 잔액: 0 - 동시 접근
Thread-4 - 현재 잔액: 0 - 동시 접근
Thread-5 - 현재 잔액: 0 - 동시 접근
Thread-8 - 입금: 5000 - 2초 대기
Thread-9 - 입금: 4000 - 2초 대기 (누적 4초 대기)
Thread-10 - 현재 잔액: 9000 - 동시 접근 (누적 4초 대기)
Thread-18 - 현재 잔액: 9000 - 동시 접근 (누적 4초 대기)
Thread-19 - 현재 잔액: 9000 - 동시 접근 (누적 4초 대기)
Thread-15 - 현재 잔액: 9000 - 동시 접근 (누적 4초 대기)
Thread-11 - 현재 잔액: 9000 - 동시 접근 (누적 4초 대기)
Thread-14 - 현재 잔액: 9000 - 동시 접근 (누적 4초 대기)
Thread-13 - 현재 잔액: 9000 - 동시 접근 (누적 4초 대기)
Thread-12 - 현재 잔액: 9000 - 동시 접근 (누적 4초 대기)
Thread-17 - 현재 잔액: 9000 - 동시 접근 (누적 4초 대기)
Thread-16 - 현재 잔액: 9000 - 동시 접근 (누적 4초 대기)
 */
