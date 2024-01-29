package org.example.chaptor05.exam4;

public class ImmutableExample implements Runnable {
    private ImmutablePerson person;

    public ImmutableExample(ImmutablePerson person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - 이름: " + person.getName() + ", 나이: " + person.getAge());
    }

    public static void main(String[] args) {
        ImmutablePerson person = new ImmutablePerson("홍길동", 25);

        for (int i=0; i<10; i++) {
            new Thread(new ImmutableExample(person)).start();
        }
    }
}

final class ImmutablePerson {
    private String name;
    private int age;

    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}