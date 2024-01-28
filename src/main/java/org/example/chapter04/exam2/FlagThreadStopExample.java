package org.example.chapter04.exam2;

public class FlagThreadStopExample {

    private static boolean running = true;
//    private volatile static boolean running = true;

    public static void main(String[] args) {

        new Thread(() -> {
            int count = 0;
            while (running) {
                try { // sleep이 존재하지 않다면, 해당 스레드의 독립적인 캐시 메모리의 값을 계속해서 가져오기 때문에 항상 running의 변화를 감지할 수 없다.
                    Thread.sleep(1); // 컨텍스트 스위칭이 일어나기 때문에 running의 변화를 감지할 수 있다.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
            }
            System.out.println("스레드 1 종료, count: " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("스레드 2 종료");
            running = false;
        }).start();
    }
}
