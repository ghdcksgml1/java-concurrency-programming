package org.example.chapter03.exam5;

public class ThreadPriorityExample {
    public static void main(String[] args) throws InterruptedException {
        CountingThread highThread = new CountingThread("우선 순위가 높은 스레드", Thread.MAX_PRIORITY);
        CountingThread normalThread = new CountingThread("우선 순위가 중간 스레드", Thread.NORM_PRIORITY);
        CountingThread lowThread = new CountingThread("우선 순위가 낮은 스레드", Thread.MIN_PRIORITY);

        highThread.start();
        normalThread.start();
        lowThread.start();

        highThread.join();
        normalThread.join();
        lowThread.join();

        System.out.println("작업 완료");
    }

    static class CountingThread extends Thread {
        private final String threadName;
        private int count = 0;

        public CountingThread(String threadName, int priority) {
            this.threadName = threadName;
            setPriority(priority);
        }

        @Override
        public void run() {
            while (count < 1000000000) {
                count++;
            }
            System.out.println(threadName + ": " + count);
        }
    }
}
