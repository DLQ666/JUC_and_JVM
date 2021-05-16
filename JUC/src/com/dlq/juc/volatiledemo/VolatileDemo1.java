package com.dlq.juc.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 *@program: ZkDemo
 *@description:
 *@author: Hasee
 *@create: 2021-05-15 12:03
 */
class MyData1 {

    volatile int number = 0;

    public void addTo60() {
        number = 60;
    }
}
/**
 * 1、验证volatile的可见性
 *    1.1 假如 int number = 0; number 变量之前根本没有添加volatile关键字修饰，没有可见性
 *    1.2 添加了volatile，可以解决可见性问题
 */
public class VolatileDemo1 {

    public static void main(String[] args) {
        MyData1 myData1 = new MyData1(); //资源类

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            //暂停一会线程
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            myData1.addTo60();
            System.out.println(Thread.currentThread().getName() + "updated number value：" + myData1.number);
        }, "AAA").start();

        //第二个线程就是我们的main线程

        while (myData1.number == 0) {
            //如果没有可见性，，main线程 所保存的值依旧是 0  就会在这里一直等待循环，直到number值不再等于零
        }

        //若这句话打印出来了，说明main主线程感知到了number的值发生了变化，那么就证明了可见性
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
