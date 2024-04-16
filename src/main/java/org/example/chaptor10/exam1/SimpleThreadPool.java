package org.example.chaptor10.exam1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SimpleThreadPool {
    private int numThreads;
    private Queue<Runnable> taskQueue;
    private Thread[] threads;
    private volatile boolean isShutdown; // 모든 스레드들이 해당 플래그를 바라보기 때문에 캐시에 값을 저장하지 않도록 volatile 키워드 설정

    public SimpleThreadPool(int numThreads) {
        this.numThreads = numThreads;
        this.taskQueue = new ConcurrentLinkedQueue<>();
        this.threads = new Thread[numThreads];
        this.isShutdown = false;

        for (int i = 0; i < this.numThreads; i++) {
            this.threads[i] = new WorkerThread();
            this.threads[i].start();
        }
    }

    public void submit(Runnable task) {
        if (!isShutdown) {
            synchronized (taskQueue) {
                taskQueue.offer(task); // taskQueue에 일거리를 제공한다.
                taskQueue.notifyAll(); // taskQueue로 대기하는 스레드들을 깨운다.
            }
        }
    }

    public void shutdown() {
        isShutdown = true; // shutDown 플래그를 true로 만든다.
        synchronized (taskQueue) {
            taskQueue.notifyAll(); // taskQueue로 대기하는 스레드들을 깨운다. 이때 남아있는 큐들의 작업을 스레드들이 가져가는데, isShutdown이 true이기 때문에 작업을 처리하지 못한다.
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // 스레드를 대기한다.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!isShutdown) {
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty() && !isShutdown) { // 큐가 비어있으면서, 종료상태가 아니면
                        try {
                            taskQueue.wait(); // taskQueue에 대기를 걸어둔다.
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if (!taskQueue.isEmpty()) { // 큐가 비어있지 않으면
                    Runnable task = taskQueue.poll(); // 일거리를 하나 가져온다.
                    task.run(); // 일을 한다.
                }
            }
        }
    }
}
