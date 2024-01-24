package org.example.chapter02.exam3;

public class NewStateThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i= 0 ; i< 100000000; i++) {
                if (i % 50000000 == 0) {
                    System.out.println("스레드 상태: " + Thread.currentThread().getState());
                }
            }
        });

        thread.start();
    }
}
