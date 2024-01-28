package org.example.chapter04.exam5;

public class InheritableThreadLocalExample {
    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        inheritableThreadLocal.set("부모 스레드의 값");

        Thread childThread = new Thread(() -> {
            System.out.println("자식 스레드에서 부모로부터 상속받은 값: " + inheritableThreadLocal.get());
            inheritableThreadLocal.set("자식 스레드의 새로운 값");
            System.out.println("자식 스레드에서 설정한 후의 값: " + inheritableThreadLocal.get());

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("자식 스레드에서 부모로부터 상속받은 새로운 값: " + inheritableThreadLocal.get());
        });
        childThread.start();
        Thread.sleep(500);
        System.out.println("부모 스레드의 값: " + inheritableThreadLocal.get());
        inheritableThreadLocal.set("부모 스레드의 새로운 값");
        childThread.join();
    }
}
