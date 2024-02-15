package org.example.chaptor09.exam2;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {
    public static void main(String[] args) throws InterruptedException {
        User user1 = new User("Alice", 25);
        User user2 = new User("Bob", 30);

        AtomicReference<User> atomicReference = new AtomicReference<>(user1);

        Thread thread1 = new Thread(() -> {
            User updateUser = new User("Carol", 40);
            boolean isSuccess = atomicReference.compareAndSet(user1, updateUser);
            if (isSuccess) {
                System.out.println("스레드1이 " + updateUser + " 로 변경에 성공했습니다.");
            } else {
                System.out.println("스레드1이 " + updateUser + " 로 변경에 실패했습니다.");
            }
        });

        Thread thread2 = new Thread(() -> {
            User updateUser = new User("David", 50);
            boolean isSuccess = atomicReference.compareAndSet(user2, updateUser);
            if (isSuccess) {
                System.out.println("스레드2가 " + updateUser + " 로 변경에 성공했습니다.");
            } else {
                System.out.println("스레드2가 " + updateUser + " 로 변경에 실패했습니다.");
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("final user: " + atomicReference.get());
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}