package org.example.chapter03.exam4;

public class ThreadNamingExample {
    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(() -> {
            System.out.println("현재 쓰레드 이름: " + Thread.currentThread().getName());
        }, "myThread");// Thread 이름 지정

        myThread.start();

        Thread yourThread = new Thread(() -> {
            System.out.println("현재 쓰레드 이름: " + Thread.currentThread().getName());
        });
        yourThread.setName("yourThread");
        yourThread.start();

        for (int i=0; i<5; i++) {
            Thread defaultThread = new Thread(() -> {
                System.out.println("현재 쓰레드 이름: " + Thread.currentThread().getName());
            });
            defaultThread.start();
        }

        Thread.sleep(2000);
    }
}
