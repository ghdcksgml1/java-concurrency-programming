package org.example.chapter02.exam2;

public class MultiThreadAppTerminatedExample {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MultiRunnable(i));
            thread.start();
        }
        System.out.println("메인 스레드 종료");
    }
}

class MultiRunnable implements Runnable {

    private final int threadId;

    public MultiRunnable(int threadId) {
        this.threadId = threadId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
        firstMethod(threadId);
    }

    private void firstMethod(int threadId) {
        int localValue = threadId + 100;
        secondMethod(localValue);
    }

    private void secondMethod(int localValue) {
        String objectReference = threadId + ": Hello World";
        System.out.println(Thread.currentThread().getName() + " : 스레드 ID : " + threadId + ", Value: " + localValue);
    }
}
