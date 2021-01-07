package com.dlq.juc.threadpool;

import java.util.concurrent.*;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2020-12-12 19:39
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try{
            //模拟有10个顾客过来银行办理业务，目前池子里有5个工作线程提供服务
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }

    private static void extracted() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个工作线程，类似一个银行有5个受理窗口
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个工作线程，类似一个银行有1个受理窗口
        //ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个工作线程，类似一个银行有N个受理窗口

        try{
            //模拟有10个顾客过来银行办理业务，目前池子里有5个工作线程提供服务
            for (int i = 0; i <= 10; i++) {
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }
}
