package org.example.chaptor07.exam1;

class BankAccount {
    private double balance;
    private final Object lock = new Object();

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        synchronized (lock) { // this.lock
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        synchronized (lock) { // this.lock
            System.out.println(this + " 2");
            if (balance < amount) {
                return false;
            }
            balance -= amount;
            return true;
        }
    }

    public boolean transfer(BankAccount to, double amount) {
        synchronized (lock) { // this.lock
            System.out.println(this + " 1");
            if (withdraw(amount)) {
                System.out.println(this + " 3");
                synchronized (to.lock) { // to.lock != this.lock
                    to.deposit(amount);
                    return true;
                }
            }
        }
        return false;
    }

    public double getBalance() {
        synchronized (lock) {
            return balance;
        }
    }
}

public class MultipleMonitorsExample {
    public static void main(String[] args) throws InterruptedException {
        synchronized (MultipleMonitorsExample.class) { // synchronized의 재진입성
            synchronized (MultipleMonitorsExample.class) {
                synchronized (MultipleMonitorsExample.class) {
                    synchronized (MultipleMonitorsExample.class) {
                        synchronized (MultipleMonitorsExample.class) {
                            synchronized (MultipleMonitorsExample.class) {
                                synchronized (MultipleMonitorsExample.class) {
                                    System.out.println("안녕"); // 잘 출력된다.
                                }
                            }
                        }
                    }
                }
            }
        }
        BankAccount accountA = new BankAccount(1000);
        BankAccount accountB = new BankAccount(1000);

        // accountA에서 accountB로 송금하는 스레드
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountA.transfer(accountB, 10);
                if (result) {
                    System.out.println("accountA에서 accountB로 10 송금 성공");
                } else {
                    System.out.println("accountA에서 accountB로 10 송금 실패");
                }
            }
        });

        // accountB에서 accountA로 송금하는 스레드
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountB.transfer(accountA, 10);
                if (result) {
                    System.out.println("accountB에서 accountA로 10 송금 성공");
                } else {
                    System.out.println("accountB에서 accountA로 10 송금 실패");
                }
            }
        });

        t1.start();
        t1.join();
        t2.start();
        t2.join();

        System.out.println("accountA 잔고: " + accountA.getBalance());
        System.out.println("accountB 잔고: " + accountB.getBalance());
    }
}
