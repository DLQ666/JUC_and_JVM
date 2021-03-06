package com.dlq.juc.readwritelock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache { //资源类
    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
        //暂停一会儿线程
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "\t 写入完成：" + key);
    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "\t 正在读取：");
        //暂停一会儿线程
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + result);
    }
}

/**
 *@program: ZkDemo
 *@description:
 *@author: Hasee
 *@create: 2021-05-17 15:42
 *
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源类，就不应该再有其它线程可以对该资源进行读或写
 * 小总结：
 *          读-读 能共存
 *          读-写 不能共存
 *          写-写 不能共存
 *
 *          写操作：原子+独占
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 5; i++) {
            final int tempint = i;
            new Thread(() -> {
                myCache.put(tempint + "", tempint + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tempint = i;
            new Thread(() -> {
                myCache.get(tempint + "");
            }, String.valueOf(i)).start();
        }

    }
}
