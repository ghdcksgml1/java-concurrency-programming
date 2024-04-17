package org.example.chaptor10.exam2;

import java.util.concurrent.Executor;

public class AsyncExecutorExample {
    public static void main(String[] args) {
        Executor syncExecutor = new AsyncExecutor();
        System.out.println(Thread.currentThread().getName() + " 시작!!");
        syncExecutor.execute(() -> System.out.println(Thread.currentThread().getName() + " 1. 안녕하슈"));
        syncExecutor.execute(() -> System.out.println(Thread.currentThread().getName() + " 2. 안녕하슈"));
    }

    static class AsyncExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }
}
