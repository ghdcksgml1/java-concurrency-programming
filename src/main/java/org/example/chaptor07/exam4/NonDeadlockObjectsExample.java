package org.example.chaptor07.exam4;

public class NonDeadlockObjectsExample {
    public static void main(String[] args) {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        Thread thread1 = new Thread(() -> {
            resourceA.methodA(resourceB);
        });

        Thread thread2 = new Thread(() -> {
            resourceB.methodB(resourceA);
        });

        thread1.start();
        thread2.start();
    }

    static class ResourceA {
        public void methodA(ResourceB resourceB) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ": methodA 실행");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
            resourceB.methodB2();
        }

        public synchronized void methodA2() {
            System.out.println(Thread.currentThread().getName() + ": methodA2 실행");
        }
    }

    static class ResourceB {
        public void methodB(ResourceA resourceB) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ": methodB 실행");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
            }
            resourceB.methodA2();
        }

        public synchronized void methodB2() {
            System.out.println(Thread.currentThread().getName() + ": methodB2 실행");
        }
    }
}

