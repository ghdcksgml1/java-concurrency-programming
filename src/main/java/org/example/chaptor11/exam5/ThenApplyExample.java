package org.example.chaptor11.exam5;

import com.chanhui.RunningTimeFormatter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ThenApplyExample {
    public static void main(String[] args) {
        MyService myService = new MyService();
        long startTime = System.currentTimeMillis();

        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 40;
        }).thenApply(result -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            int data = myService.getData1();
            return result + data;
        }).thenApplyAsync(result -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            int data = myService.getData2();
            return result + data;
        });

        int finalResult = cf.join();
        System.out.println("총 소요 시간 : " + RunningTimeFormatter.format(System.currentTimeMillis() - startTime));
        System.out.println("finalResult = " + finalResult);
    }

    static class MyService {
        public int getData1() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 50;
        }

        public int getData2() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 12;
        }
    }
}
