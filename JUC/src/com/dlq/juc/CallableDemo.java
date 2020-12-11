package com.dlq.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("我是创建多线程第二种----第一种是继承Thread类");
    }
}

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("****创建多线程第三种---come in Callable call method()");
        return 1024;
    }

}

/**
 * @program: JUC_JVM
 * @description:
 * @author: Hasee
 * @create: 2020-07-22 14:14
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask(new MyThread());

        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();

        System.out.println(Thread.currentThread().getName()+"计算完成");

        System.out.println(futureTask.get());

    }
}
