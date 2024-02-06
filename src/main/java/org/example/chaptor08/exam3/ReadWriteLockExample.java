package org.example.chaptor08.exam3;

import org.example.chaptor06.exam1.SharedData;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        SharedData sharedData = new SharedData();

        Thread reader = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("읽기 스레드가 데이터를 읽고 있습니다. 데이터: " + sharedData.getData());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread reader2 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("읽기 스레드 2가 데이터를 읽고 있습니다. 데이터: " + sharedData.getData());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread writer = new Thread(() -> {
            readWriteLock.writeLock().lock();
            try {
                System.out.println("쓰기 스레드가 데이터를 쓰고 있습니다. 데이터: " + sharedData.getData());
                sharedData.setData(40);
                System.out.println("쓰기 스레드가 데이터를 변경 했습니다. 데이터: " + sharedData.getData());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });

        reader.start();
        reader2.start();
        writer.start();

        reader.join();
        reader2.join();
        writer.join();
    }

    static class SharedData {
        private int data = 0;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
