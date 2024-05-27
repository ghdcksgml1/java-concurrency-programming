package org.example.chaptor11.exam12;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Integer> {
    private final int[] array;
    private final int start;
    private final int end;

    private static final int THRESHOLD = 2;
    public CustomRecursiveTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        System.out.println(String.format("[%s] : compute %s", Thread.currentThread().getName(), this));
        if (end - start < THRESHOLD) {
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            System.out.println(String.format("[%s] : sum = %d + %d", Thread.currentThread().getName(), start, end));
            return sum;
        } else {
            int mid = start + (end - start) / 2;
            CustomRecursiveTask left = new CustomRecursiveTask(array, start, mid);
            CustomRecursiveTask right = new CustomRecursiveTask(array, mid, end);

            left.fork();
            int rightResult = right.compute();
            int leftResult = left.join();
            return leftResult + rightResult;
        }
    }

    @Override
    public String toString() {
        return "CustomRecursiveTask{" +
                "array=" + Arrays.toString(array) +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
