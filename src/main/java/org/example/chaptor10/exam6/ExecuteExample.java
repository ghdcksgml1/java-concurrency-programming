package org.example.chaptor10.exam6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            System.out.println("비동기 작업 실행");
        });

        executorService.shutdown();
    }
}
