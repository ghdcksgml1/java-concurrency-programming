package org.example.chaptor11.exam4;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class RunAsyncExample {
    public static void main(String[] args) {
        MyService2 myService = new MyService2();

        System.out.println("=============completable future=============");

        CompletableFuture.runAsync(() -> {
            System.out.println("[" + Thread.currentThread().getName() + "] 가 비동기 작업을 시작합니다.");
            myService.getData().forEach(System.out::println);
        }).join();

        System.out.println("메인 스레드 종료");
    }
}

class MyService2 {
    public List<Integer> getData() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(1, 2, 3);
    }
}
