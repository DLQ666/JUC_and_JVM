package com.dlq.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @program: JUC_JVM
 * @description: 集合类不安全
 * @author: Hasee
 * @create: 2020-07-21 16:30
 *
 * 举例说明集合类是不安全的
 *
 * 1、故障现象
 *  java.util.ConcurrentModificationException
 *
 * 2、导致原因
 *      并发争抢修改导致，参考签名情况。
 *      一个人正在写入，另外一个同学过来抢夺，导致数据不一致异常。并发修改异常。
 *
 * 3、解决方法
 *      listNotSafe::::
 *      3.1、new Vector<>()
 *      3.2、Collections.synchronizedList(new ArrayList<>())
 *      3.3、new CopyOnWriteArrayList<>();
 *
 *      setNotSafe::::
 *      Collections.synchronizedSet(new HashSet<>())
 *      new CopyOnWriteArraySet<>()
 *
 *      mapNotSafe::::
 *      3.4、Collections.synchronizedMap(new HashMap<>());
 *      3.5、new ConcurrentHashMap<>();
 *
 * 4、优化建议（同样的错误不犯第二次）
 *      CopyOnWriteArrayList
 *      CopyOnWriteArraySet
 *      ConcurrentHashMap
 */
public class NotSafeDemo03 {
    public static void main(String[] args) {

        //map集合线程不安全演示
        /*Map<String,String> map = new HashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }*/

        //set集合线程不安全演示
        /*Set<Object> set = new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }*/

        //list集合线程不安全演示
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    private static void mapSafe() {
        Map<String, String> hashMap = new ConcurrentHashMap<>();//Collections.synchronizedMap(new HashMap<>());
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                hashMap.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(hashMap);
            }, String.valueOf(i)).start();
        }
    }

    private static void setSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>());
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    /** 笔记
     * 写时复制
       CopyOnWrite容器 即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器object[]进行Copy，
       复制出一个新的容器 Object[] newElements，然后往新容器 Object[] newElements 里添加元素，添加完元素后，
       再将原容器的引用指向新的容器 setArray(newElements);。这样做的好处是可以对CopyOnWrite容器进行并发的读，
       而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
     *
     */
    private static void listSafe() {
        //写时复制->解决多线程环境下List集合线程不安全问题
        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
















/** 笔记
 *  写时复制
  CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行Copy，
  复制出一个新的容器Object[] newElements,然后往新的容器Object[] newElements里添加元素，添加完元素之后，
  再将原容器的引用指向新的容器 setArray(newElements)；。这样做的好处是可以对CopyOnWrite容器进行并发的读，
  而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器
public boolean add(E e) {

    final ReentrantLock lock = this.lock;

    lock.lock();

    try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
    } finally {
        lock.unlock();
    }
}
*/



