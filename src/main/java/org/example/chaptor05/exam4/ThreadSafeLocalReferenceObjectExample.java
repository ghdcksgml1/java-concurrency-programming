package org.example.chaptor05.exam4;

public class ThreadSafeLocalReferenceObjectExample {

//        LocalObject localObject = new LocalObject();
    class LocalObject {
        private int value;

        public void increment() {
            value++;
        }

        @Override
        public String toString() {
            return "LocalObject{" + "value=" + value + "}";
        }
    }

    public void useLocalObject() {
        // 지역 객체 참조. 각 스레드는 이 객체의 독립된 인스턴스를 가짐.
        LocalObject localObject = new LocalObject();

        for (int i=0; i<50; i++) {
            localObject.increment();
            System.out.println(Thread.currentThread().getName() + " - " + localObject);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadSafeLocalReferenceObjectExample example = new ThreadSafeLocalReferenceObjectExample();

        new Thread(() -> {
            example.useLocalObject();
        }).start();

        new Thread(() -> {
            example.useLocalObject();
        }).start();
    }
}
