package com.dlq.juc.volatiledemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *@program: ZkDemo
 *@description:
 *@author: Hasee
 *@create: 2021-05-15 15:52
 */
class MyData4 {

    volatile int number = 0;

    public void addTo60() {
        number = 60;
    }

    //请注意，此时number 前面是加了 volatile关键字修饰的，volatile不保证原子性
    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 *    2.4 如何解决原子性？
 *          加 sync
 *          使用我们JUC 下AtomicInteger
 */
public class VolatileDemo4 {
    public static void main(String[] args) {
        MyData4 myData4 = new MyData4();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData4.addPlusPlus();
                    myData4.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //需要等待上面20个线程都全部计算完成，再用 main 线程取得最终的结果值看是多少？》
        //暂停一会线程
        while (Thread.activeCount() > 2) {//为啥是2  因为后台默认有俩线程 main和GC线程 >2
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() +
                "\t int type , finally  number value：" + myData4.number);
        System.out.println(Thread.currentThread().getName() +
                "\t AtomicInteger type , finally  number value：" + myData4.atomicInteger);
    }
}
