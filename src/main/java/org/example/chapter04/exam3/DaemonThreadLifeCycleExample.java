package org.example.chapter04.exam3;

public class DaemonThreadLifeCycleExample {
    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("사용자 스레드 실행 중..");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("유저 스레드가 종료 되었습니다.");
        });

        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    System.out.println("데몬 스레드 실행 중");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();

//        userThread.join();

        System.out.println("메인 스레드 종료");
    }
}
