package org.example.chapter04.exam1;

public class UncaughtExceptionHandlerExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("스레드 시작!");

            throw new RuntimeException("예기치 않은 예외!");
        });

        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " 에서 예외 발생 " + e);
        });

        thread.start();

        Thread thread2 = new Thread(() -> {
            System.out.println("스레드 2 시작!");

            throw new RuntimeException("예기치 않은 예외!");
        });

        thread2.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " 에서 예외 발생 " + e);
        });

        thread2.start();
    }
}
