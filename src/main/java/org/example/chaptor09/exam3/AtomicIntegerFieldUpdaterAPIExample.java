package org.example.chaptor09.exam3;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdaterAPIExample {

    private static AtomicIntegerFieldUpdater<MyClass> fieldUpdater1;
    private static AtomicReferenceFieldUpdater<MyClass, String> fieldUpdater2;

    public static void main(String[] args) {
        fieldUpdater1 = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "field1");
        fieldUpdater2 = AtomicReferenceFieldUpdater.newUpdater(MyClass.class, String.class, "field2");

        MyClass myClass = new MyClass();
        fieldUpdater1.addAndGet(myClass, 40); // 원자성 보장
        fieldUpdater2.compareAndSet(myClass, null, "myField");

        System.out.println("업데이트 결과: " + myClass.getField1());
        System.out.println("업데이트 결과: " + myClass.getField2());
   }
    static class MyClass {
        private volatile int field1;
        private volatile String field2;

        public int getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }
    }
}

