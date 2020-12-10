package com.dlq.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{ //资源类 = 实例变量 + 实例方法

    private int number = 30;
    //List list = new ArrayList();
    Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第: " + (number--) + "\t 还剩下：" + number);
            }
        } finally {
            lock.unlock();
        }
    }

}

/**
 * @program: JUC_JVM
 * @description:
 * @author: Hasee
 * @create: 2020-07-21 13:41
 *
 * 题目：三个售票员         卖出          30张票
 * 笔记：如何编写<企业级>的多线程代码
 *  固定的编程套路+模板是什么？
 *
 *  1、在高内聚低耦合的前提下，线程       操作        资源类
 *
 *      1.1、一言不合，先创建一个资源类
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();

//        @FunctionalInterface
//        public interface Runnable {
//            public abstract void run();
//        }

        new Thread(() -> {for (int i = 0; i <=40 ; i++) ticket.sale();},"A").start();
        new Thread(() -> {for (int i = 0; i <=40 ; i++) ticket.sale();},"B").start();
        new Thread(() -> {for (int i = 0; i <=40 ; i++) ticket.sale();},"C").start();



//        Thread(Runnable target, String name)  Allocates a new Thread object.
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <=40 ; i++) {
                    ticket.sale();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <=40 ; i++)
                {
                    ticket.sale();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <=40 ; i++) {
                    ticket.sale();
                }
            }
        },"C").start();*/

    }
}
