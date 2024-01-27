package org.example.chapter03.exam02;

public class MultiThreadJoinExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("스레드1가 3초 동안 작동합니다.");
                Thread.sleep(3000);
                System.out.println("스레드1 작동 완료.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("스레드2가 2초 동안 작동합니다.");
                Thread.sleep(2000);
                System.out.println("스레드2 작동 완료.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        System.out.println("메인 스레드가 다른 스레드의 완료를 기다린다.");
        thread1.join(2500);
        thread2.join(2500);
        System.out.println("메인 스레드가 계속 작업을 진행합니다.");
    }
}
