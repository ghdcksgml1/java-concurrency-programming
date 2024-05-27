package org.example.chaptor11.exam12;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CustomForkJoinPoolExample {
    public static void main(String[] args) {
        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        RecursiveTask<Integer> task = new CustomRecursiveTask(array, 0, 100000);
        Integer result = forkJoinPool.invoke(task);

        System.out.println("result = " + result);
    }
}
