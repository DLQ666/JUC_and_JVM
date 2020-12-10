package com.dlq.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SyncAircondition{

    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //1、判断
        while (number != 0){
            this.wait();
        }
        //2、干活
        number ++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3、通知 所有线程唤醒
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        //1、判断
        while (number == 0){
            this.wait();
        }
        //2、干活
        number --;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3、通知
        this.notifyAll();
    }
}

class LockAircondition{

    private int number = 0;

    private Lock lock = new ReentrantLock();  //可重入非公平递归锁
    final Condition condition  = lock.newCondition();

    public void increment()throws Exception{

        lock.lock();
        try {
            //1、判断
            while (number != 0){
                condition.await();  //this.wait();
            }
            //2、干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+ number);
            //3、通知
            condition.signalAll();  //this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement()throws Exception{

        lock.lock();
        try {
            //1、判断
            while (number == 0){
                condition.await();  //this.wait();
            }
            //2、干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+ number);
            //3、通知
            condition.signalAll();  //this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
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
    3、  多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while，不能用if）

    知识点小总结 = 多线程编程套路 + while判断 + 新版写法
 */
public class ProdConsumerDemo04 {

    public static void main(String[] args) throws Exception {

        //资源类
        LockAircondition aircondition = new LockAircondition();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
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
