package org.example.chaptor08.exam6;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerProducerExample {
    private static int CAPACITY = 5;
    private Queue<Integer> queue = new LinkedList<>();
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public void produce() {
        int value = 0;
        while (true) {
            lock.lock();

            try {
                while (queue.size() >= CAPACITY) {
                    System.out.println("큐가 가득차서 대기");
                    notFull.await();
                }
                queue.offer(value);
                System.out.println("생산: " + value + ", 큐의 크기: " + queue.size());
                notEmpty.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    public void consume() {
        while (true) {
            lock.lock();

            try {
                while (queue.isEmpty()) {
                    System.out.println("큐가 비어서 대기");
                    notEmpty.await();
                }
                Integer value = queue.poll();
                System.out.println("소비: " + value + ", 큐 크기: " + queue.size());
                notFull.signal();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ConsumerProducerExample example = new ConsumerProducerExample();

        Thread thread1 = new Thread(() -> {
            example.produce();
        });

        Thread thread2 = new Thread(() -> {
            example.consume();
        });

        thread1.start();
        thread2.start();
    }
}
