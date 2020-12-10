package com.dlq.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

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
 *
 * 3、解决方法
 *      listNotSafe::::
 *      3.1、new Vector<>()
 *      3.2、Collections.synchronizedList(new ArrayList<>())
 *      3.3、new CopyOnWriteArrayList<>();
 *
 *      setNotSafe::::
 *      3.4、Collections.synchronizedMap(new HashMap<>());
 *      3.5、new ConcurrentHashMap<>();
 *
 * 4、优化建议（同样的错误不犯第二次）
 *      CopyOnWriteArrayList
 */
public class NotSafeDemo03 {
    public static void main(String[] args) {

        /*//list集合不安全演示
        List<Object> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }*/
        listSafe();
    }

    private static void setSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listSafe() {
        //写时复制->解决多线程环境下List集合线程不安全问题
        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());//new Vector<>();//new ArrayList<>();

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



