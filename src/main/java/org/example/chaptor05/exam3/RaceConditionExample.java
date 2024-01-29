package org.example.chaptor05.exam3;

public class RaceConditionExample {
    private static int sharedResource = 0;
    public static void main(String[] args) throws InterruptedException {
        // 스레드 100개를 생성하여 공유 리소스를 동시에 증가시킴
        Thread[] incrementThreads = new Thread[100];

        for (int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    sharedResource++; // 각 스레드가 공유 데이터에 동시에 접근할 수 있고, CPU 할당을 받기 위해 경쟁하게 된다.
                }
            });
            incrementThreads[i].start();
        }

        // 모든 스레드가 작업을 완료할 때까지 기다림
        for (int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i].join();
        }

        System.out.println("sharedResource = " + sharedResource);
    }
}
