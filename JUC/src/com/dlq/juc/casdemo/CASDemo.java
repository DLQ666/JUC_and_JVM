package com.dlq.juc.casdemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-05 21:19
 *
 * 1   CAS是什么？ ===> compareAndSet
 *      比较并交换
 */
public class CASDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2020) + " current data：" + atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 1024) + " current data：" + atomicInteger.get());

        atomicInteger.getAndIncrement();
    }
}
