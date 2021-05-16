package com.dlq.juc.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 *@program: ZkDemo
 *@description:
 *@author: Hasee
 *@create: 2021-05-15 14:48
 */
class MyData2 {

    volatile int number = 0;

    public void addTo60() {
        number = 60;
    }

    //请注意，此时number 前面是加了 volatile关键字修饰的，volatile不保证原子性
    public void addPlusPlus() {
        number++;
    }

}
/**
 * 2、验证volatile不保证原子性
 *    2.1 原子性指的是什么意思？
 *         不可分割、完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整
 *         要么同时成功，要么同时失败。
 *
 *    2.2 volatile不保证原子性案例演示
 */
public class VolatileDemo2 {
    public static void main(String[] args) {
        MyData2 myData2 = new MyData2();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j < 1000; j++) {
                    myData2.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        //需要等待上面20个线程都全部计算完成，再用 main 线程取得最终的结果值看是多少？》
        //暂停一会线程
        while (Thread.activeCount() > 2) {//为啥是2  因为后台默认有俩线程 main和GC线程 >2
            Thread.yield();
        }

        //若这句话打印出来了，说明main主线程感知到了number的值发生了变化，那么就证明了可见性
        System.out.println(Thread.currentThread().getName() + "\t finally  number value：" + myData2.number);

    }
}
