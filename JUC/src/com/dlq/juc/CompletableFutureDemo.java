package com.dlq.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-01-08 14:33
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {

        /*CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值，update mysql ok");
        });
        completableFuture.get();*/

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值，insert mysql ok");
//            int age = 10 / 0;
            return 1024;
        });

        Integer integer = integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("=====t：" + t);
            System.out.println("=====u：" + u);
        }).exceptionally(f -> {
            System.out.println("*****exception：" + f.getMessage());
            return 404;
        }).get();
        System.out.println(integer);
    }
}
