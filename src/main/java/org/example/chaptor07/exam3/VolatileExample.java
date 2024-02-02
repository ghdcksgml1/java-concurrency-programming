package org.example.chaptor07.exam3;

public class VolatileExample {

    volatile boolean running = true; // volatile이 없으면, 가시성이 확보되지 않아 스레드가 종료되지 않음.

    public void volatileTest() {
        new Thread(() -> {
            int count = 0;
            while (running) {
                count++;
            }
            System.out.println("Thread 1 종료. Count: " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {

            }
            System.out.println("Thread 2 종료 중..");
            running = false;
        }).start();
    }
    public static void main(String[] args) {
        new VolatileExample().volatileTest();
    }
}
