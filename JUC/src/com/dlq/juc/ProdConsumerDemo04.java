package com.dlq.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition{

    private int number = 0;

    private Lock lock = new ReentrantLock();

    public synchronized void increment()throws Exception{

        //1、判断
        while (number != 0){
            this.wait();
        }
        //2、干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+ number);
        //3、通知
        this.notifyAll();
    }

    public synchronized void decrement()throws Exception{
        //1、判断
        while (number == 0){
            this.wait();
        }
        //2、干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+ number);
        //3、通知
        this.notifyAll();
    }

}

/**
 * @program: JUC_JVM
 * @description:
 * @author: Hasee
 * @create: 2020-07-21 20:43
 *
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零。

    1、  高聚低合前提下，线程操作资源类
    2、  判断/干活/通知
    3、  防止虚假唤醒 千万不能用if判断 --> 如果忘了就TM再看一遍阳哥视频补补
 */
public class ProdConsumerDemo04 {

    public static void main(String[] args) throws Exception {

        Aircondition aircondition = new Aircondition();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
//                    Thread.sleep(1000);
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
//                    Thread.sleep(200);
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Thread.sleep(300);
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();

    }
}
