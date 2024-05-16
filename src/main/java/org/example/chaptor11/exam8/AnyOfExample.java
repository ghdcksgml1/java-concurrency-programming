package org.example.chaptor11.exam8;

import java.util.concurrent.CompletableFuture;

public class AnyOfExample {
    public static void main(String[] args) {
        AllOfExample.ServiceA serviceA = new AllOfExample.ServiceA();
        AllOfExample.ServiceB serviceB = new AllOfExample.ServiceB();
        AllOfExample.ServiceC serviceC = new AllOfExample.ServiceC();

        CompletableFuture<Integer> cf1 = serviceA.getData1();
        CompletableFuture<Integer> cf2 = serviceB.getData2();
        CompletableFuture<Integer> cf3 = serviceC.getData3();

        Integer result = (Integer) CompletableFuture.anyOf(cf1, cf2, cf3).join();
        System.out.println("result = " + result);
        System.out.println("메인 스레드 종료");
    }

    static class ServiceA {
        public CompletableFuture<Integer> getData1() {
            // 비동기 작업 시뮬레이션
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return 10;
            });
        }
    }

    static class ServiceB {
        public CompletableFuture<Integer> getData2() {
            // 비동기 작업 시뮬레이션
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return 20;
            });
        }
    }

    static class ServiceC {
        public CompletableFuture<Integer> getData3() {
            // 비동기 작업 시뮬레이션
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return 30;
            });
        }
    }
}
