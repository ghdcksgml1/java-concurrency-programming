package org.example.chapter03.exam4;

public class CurrentThreadExample {
    public static void main(String[] args) {
        System.out.println("현재 스레드: " + Thread.currentThread());
        System.out.println("현재 스레드 이름: " + Thread.currentThread().getName());

        Thread thread = new Thread() {
            @Override
            public void run() {
                // 직접 Override를 구현하는 경우 바로 메소드 접근이 가능하다.
                System.out.println("현재 스레드: " + currentThread());
                System.out.println("현재 스레드: " + getName());
            }
        };
        thread.start();

        Thread thread1 = new Thread(new ThreadName());
        thread1.start();
    }

    static class ThreadName implements Runnable {

        @Override
        public void run() {
            System.out.println("현재 스레드 (Runnable): " + Thread.currentThread());
            System.out.println("현재 스레드 (Runnable): " + Thread.currentThread().getName());
        }
    }
}
