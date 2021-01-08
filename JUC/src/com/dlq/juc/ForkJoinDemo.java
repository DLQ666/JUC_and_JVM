package com.dlq.juc;

import javax.swing.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-01-08 13:26
 */
class MyTask extends RecursiveTask<Integer> {

    private static final Integer MY_VALUE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= MY_VALUE) {
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            int middle = (begin + end) >> 1;
            System.out.println(middle);
            MyTask myTask = new MyTask(begin, middle);
            MyTask myTask1 = new MyTask(middle + 1, end);
            myTask.fork();
            myTask1.fork();
            result = myTask.join() + myTask1.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws Exception {
        MyTask myTask = new MyTask(0, 15);
        ForkJoinPool threadPool = new ForkJoinPool();

        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);
        System.out.println(forkJoinTask.get());
        threadPool.shutdown();
    }
}
