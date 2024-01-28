package org.example.chapter04.exam2;

public class InterruptedExceptionThreadStopExample4 {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("작업 스레드가 실행 중입니다.");
                    System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());

                    if (Thread.currentThread().isInterrupted()) throw new InterruptedException("Thread is Interrupted");
                }
            } catch (InterruptedException e) { // 우리가 직접 발생시키는 예외는 interrupted 상태를 true -> false로 바꾸지 않는다.
                System.out.println("인터럽트 상태 2: " + Thread.currentThread().isInterrupted()); // true
            }
            System.out.println("작업 스레드가 중단되었습니다.");
            System.out.println("인터럽트 상태 3: " + Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
            System.out.println("중단 스레드가 작업 스레드를 중단시켰습니다.");
        });

        worker.start();
        stopper.start();
    }
}
