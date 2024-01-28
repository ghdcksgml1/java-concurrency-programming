package org.example.chapter04.exam3;

public class UserThreadLifecycleExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("사용자 스레드 1번이 실행 중...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("사용자 스레드 1 종료..");
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("사용자 스레드 2번이 실행 중...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("사용자 스레드 2 종료..");
        });

        thread.start();
        thread2.start();

        System.out.println("메인 스레드 종료");
    }
}
