package org.example.chapter02.exam2;

public class ThreadStartRunExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
        });

        thread.start();
        System.out.println(Thread.currentThread().getName() + " : 메인 스레드 종료");
    }
}
