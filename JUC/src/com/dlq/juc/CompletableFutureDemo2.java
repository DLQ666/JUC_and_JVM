package com.dlq.juc;

import java.util.concurrent.*;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-01-28 12:01
 */
public class CompletableFutureDemo2 {
    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main.....start......");
        /*CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }, executor);
        System.out.println("main.....end......");*/

       /* CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor);
        Integer integer = future.get();
        System.out.println("main.....end......"+integer);*/

        /**
            方法完成后的感知
         */
        /*CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).whenComplete((resp,exception)->{
            //虽然能得到异常信息，但是没法修改返回数据。
            System.out.println("异步任务成功完成了.....结果是："+resp+"；异常是："+exception);
        }).exceptionally(throwable -> {
            //可以感知异常，同时返回默认值
            return 10;
        });
        Integer integer = future.get();
        System.out.println("main.....end......"+integer);*/

        /**
         * 方法完成后的处理
         */
        /*CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).handle((resp,exception)->{
            System.out.println("异步任务成功完成了.....结果是："+resp+"；异常是："+exception);
            if (resp != null){
                return resp*2;
            }
            if (exception != null){
                return 0;
            }
            return 0;
        });
        Integer integer = future.get();
        System.out.println("main.....end......"+integer);*/

        /*CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务2启动了....." + res);
            return "Hello " + res;
        }, executor);
        String s = future.get();
        System.out.println("main.....end......" + s);*/

        /**
         * 两个都完成
         */
        /*CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("任务1结束：");
            return i;
        }, executor);
        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程：" + Thread.currentThread().getId());
            System.out.println("任务2结束：");
            return "Hello";
        }, executor);

        *//*future01.runAfterBothAsync(future02,()->{
            System.out.println("任务3开始。。。。。。");
        },executor);*//*
       *//* future01.thenAcceptBothAsync(future02,(f1,f2)->{
            System.out.println("任务3开始...之前的结果：" +f1+ "--》" +f2);
        },executor);*//*
        CompletableFuture<String> future = future01.thenCombineAsync(future02, (f1, f2) -> {
            return f1 + "：" + f2 + " -> Haha";
        }, executor);
        System.out.println("main.....end......"+future.get());*/

        /**
         * 两个任务，只要有一个完成，我们就执行任务3
         */
        /*CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("任务1结束：");
            return i;
        }, executor);
        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2线程：" + Thread.currentThread().getId());
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("任务2结束：");
            return "Hello";
        }, executor);
        *//*future01.runAfterEitherAsync(future02,()->{
                System.out.println("任务3开始...");
        },executor);*//*
        *//*future01.acceptEitherAsync(future02, res -> {
            System.out.println("任务3开始...之前的结果：" + res);
        }, executor);*//*
        CompletableFuture<String> future = future01.applyToEitherAsync(future02, res -> {
            System.out.println("任务3开始...之前的结果：" + res);
            return res.toString() + "->哈哈哈哈";
        }, executor);
        System.out.println("main.....end......  "+future.get());*/

        /**
         * 多任务组合
         */
        CompletableFuture<Object> futureImg = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的图片信息");
            return "hello.jpg";
        }, executor);
        CompletableFuture<Object> futureAttr = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询商品的属性信息");
            return "黑色 256G";
        }, executor);
        CompletableFuture<Object> futureDesc = CompletableFuture.supplyAsync(() -> {
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println("查询商品的介绍");
            return "介绍啊啊啊啊啊啊啊";
        }, executor);
        /*CompletableFuture<Void> allOf = CompletableFuture.allOf(futureImg, futureAttr, futureDesc);
        allOf.get(); // 等待所有结果完成
        System.out.println("main.....end...... "+futureImg.get()+"=>"+futureAttr.get()+"=>"+futureDesc.get());*/
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(futureImg, futureAttr, futureDesc);
        anyOf.get(); // 等待一个完成
        System.out.println("main.....end...... "+anyOf.get());
    }
}
