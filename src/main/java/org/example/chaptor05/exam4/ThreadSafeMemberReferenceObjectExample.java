package org.example.chaptor05.exam4;

public class ThreadSafeMemberReferenceObjectExample {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new MyRunnable(new Company("User"))).start(); // 스레드 안전, 멤버변수 공유 X
        new Thread(new MyRunnable(new Company("User"))).start(); // 스레드 안전, 멤버변수 공유 X

        Thread.sleep(1000);
        System.out.println("===================================");

        Company company = new Company("User"); // 스레드 안전하지 못함, 멤버변수를 공유함.
        new Thread(new MyRunnable(company)).start();
        new Thread(new MyRunnable(company)).start();
    }
}

class MyRunnable implements Runnable {
    private Company company;

    public MyRunnable(Company company) {
        this.company = company;
    }

    @Override
    public void run() {
        this.company.changeName(Thread.currentThread().getName());
    }
}

class Company {
    private Member member;

    public Company(String name) {
        this.member = new Member(name);
    }

    public synchronized void changeName(String name) {
        String oldName = member.getName();
        member.setName(name);
        System.out.println(oldName + ": " + member.getName());
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}