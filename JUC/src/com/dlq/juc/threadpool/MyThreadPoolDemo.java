package com.dlq.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2020-12-12 19:39
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(5);


    }
}
