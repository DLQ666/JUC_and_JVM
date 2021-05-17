package com.dlq.juc.reenterlock;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@program: ZkDemo
 *@description:
 *@author: Hasee
 *@create: 2021-05-17 13:05
 *  可重入锁(也叫做递归锁)
 *
 *  指的是同一线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，
 *  在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁
 *
 *  也即是说，线程可以进入任何一个它已经拥有的锁所同步着的代码块。
 *
 *  ReentrantLock就是一个典型的可重入锁
 *  t3	 invoked get()
 *  t3	 ########invoked set()
 *  t4	 invoked get()
 *  t4	 ########invoked set()
 */
class Phone2 implements Runnable {

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        //lock.lock();
        try {
            // 线程可以进入任何一个它已经拥有的锁
            //
            // 所同步着的代码块
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        } finally {
            lock.unlock();
            //lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t ########invoked set()");
        } finally {
            lock.unlock();
        }
    }
}

public class ReenterLockDemo2 {
    public static void main(String[] args) {
        Phone2 phone2 = new Phone2();
        Thread t3 = new Thread(phone2, "t3");
        Thread t4 = new Thread(phone2, "t4");
        t3.start();
        t4.start();
    }
}
