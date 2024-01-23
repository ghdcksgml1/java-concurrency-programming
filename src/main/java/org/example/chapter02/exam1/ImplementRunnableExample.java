package org.example.chapter02.exam1;

public class ImplementRunnableExample {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable).start();
    }
}
class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
    }
}