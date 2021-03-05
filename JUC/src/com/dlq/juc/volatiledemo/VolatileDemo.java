package com.dlq.juc.volatiledemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@program: JUC_JVM
 *@description: volatile
 *@author: Hasee
 *@create: 2021-03-04 20:53
 */
class MyData{

    volatile int number = 0;

    public void addTo60(){
        number = 60;
    }

    //请注意，此时number 前面是加了 volatile关键字修饰的，volatile不保证原子性
    public void addPlusPlus(){
        number ++ ;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }

}

/**
 * 1、验证volatile的可见性
 *    1.1 假如 int number = 0; number 变量之前根本没有添加volatile关键字修饰，没有可见性
 *    1.2 添加了volatile，可以解决可见性问题
 * 2、验证volatile不保证原子性
 *    2.1 原子性指的是什么意思？
 *         不可分割、完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整
 *         要么同时成功，要么同时失败。
 *
 *    2.2 volatile不保证原子性案例演示
 *
 *    2.3 why
 *
 *    2.4 如何解决原子性？
 *          * 加sync
 *          * 使用我们JUC 下AtomicInteger
 *
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <=1000; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //需要等待上面20个线程全部计算完成后，再用main线程取得最终的结束值看是多少？
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"int type, finally number value --->" + myData.number);
        System.out.println(Thread.currentThread().getName()+"AtomicInteget type, finally number value --->" + myData.atomicInteger);

    }

    private static void seeOKByVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"come in");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"update number value:"+myData.number);
        }, "AAA").start();

        while (myData.number == 0){
            //main线程 就会一直在这里等待循环，直到number值被更改
        }
        System.out.println(Thread.currentThread().getName()+"miss is over");
    }


}
