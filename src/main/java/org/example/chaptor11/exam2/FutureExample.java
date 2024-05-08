package org.example.chaptor11.exam2;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<Integer> future1 = executorService.submit(new Service1());
        Future<Integer> future2 = executorService.submit(new Service2(future1));
        Future<Integer> future3 = executorService.submit(new Service3(future2));
        Future<Integer> future4 = executorService.submit(new Service4(future3));
        Future<Integer> future5 = executorService.submit(new Service5(future4));

        // 최종 결과를 얻기 위해 future5의 완료를 기다림
        int finalResult = future5.get();

        executorService.shutdown();
        System.out.println("최종 결과: " + finalResult);
    }

    static class Service1 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("Service1 시작");
            return 1;
        }
    }

    static class Service2 implements Callable<Integer> {
        private Future<Integer> future;

        public Service2(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service2 시작");
            return future.get() + 1;
        }
    }

    static class Service3 implements Callable<Integer> {
        private Future<Integer> future;

        public Service3(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service3 시작");
            return future.get() + 1;
        }
    }

    static class Service4 implements Callable<Integer> {
        private Future<Integer> future;

        public Service4(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service4 시작");
            return future.get() + 1;
        }
    }

    static class Service5 implements Callable<Integer> {
        private Future<Integer> future;

        public Service5(Future<Integer> future) {
            this.future = future;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("Service5 시작");
            return future.get() + 1;
        }
    }
}
