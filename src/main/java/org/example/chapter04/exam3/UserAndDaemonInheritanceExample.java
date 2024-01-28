package org.example.chapter04.exam3;

public class UserAndDaemonInheritanceExample {
    public static void main(String[] args) {
        Thread userThread = new Thread(() -> {
            new Thread(() -> {
                System.out.println("사용자 스레드의 자식 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            }).start();
            System.out.println("사용자 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });

        Thread daemonThread = new Thread(() -> {
            new Thread(() -> {
                System.out.println("데몬 스레드의 자식 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            }).start();
            System.out.println("데몬 스레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });
        userThread.start();
        daemonThread.setDaemon(true);
        daemonThread.start();
    }
}
