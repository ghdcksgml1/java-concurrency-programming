package org.example.chaptor08.exam3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

public class BankAccount {
    private final ReadWriteLock lock;
    private Map<String, Integer> balance;

    public BankAccount(ReadWriteLock lock) {
        this.lock = lock;
        this.balance = new HashMap<>() {{
            put("account1", 0);
        }};
    }

    // 잔고 확인
    public int getBalance() {
        lock.readLock().lock();
        try {
            return balance.get("account1");
        } finally {
            lock.readLock().unlock();
        }
    }

    // 입금
    public void deposit(int amount) {
        lock.writeLock().lock();
        try {
            Thread.sleep(2_000);
            int currentBalance = balance.get("account1");
            currentBalance += amount;
            balance.put("account1", currentBalance);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
