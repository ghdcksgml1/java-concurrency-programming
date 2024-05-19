package org.example.chaptor11.exam10;

import java.util.concurrent.CompletableFuture;

public class IsCompletedExceptionallyAndIsCancelledExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> {
//            return 20;
            throw new RuntimeException("error");
        });

//        boolean cancel = cf2.cancel(true); // 완료되지 않았을 경우에만 취소된다.
//        System.out.println("cancel = " + cancel);

        CompletableFuture<Integer> combineFuture = cf1.thenCombine(cf2.exceptionally(e -> 15), (result1, result2) -> {
            if (cf2.isCancelled()) {
                return 0; // 취소 완료
            } else if (cf2.isCompletedExceptionally()) {
                return result2; // 예외 완료
            } else if (cf2.isDone()) {
                return result1 + result2; // 정상 완료
            } else return -1;
        });

        Integer result = combineFuture.join();
        System.out.println("result = " + result);
    }
}
