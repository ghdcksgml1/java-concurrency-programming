package org.example.chapter03.exam3;

public class InterruptedExample3 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("스레드 작동 중..");
                if (Thread.interrupted()) {
                    System.out.println("인터럽트 상태가 초기화 되었습니다.");
                    break;
                }
            }
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());

            // 인터럽트 상태라고해서 현재 스레드가 중단되거나 하지 않는다.
            // 인터럽트된 스레드가 처리해야하는 특별한 규칙이나 정해진 기준은 없다.
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
            }
        });
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
