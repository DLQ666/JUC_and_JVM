package com.dlq.juc.volatiledemo;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-04 13:10
 */
public class SingletonVolatile {

    private static volatile SingletonVolatile instance = null;

    private SingletonVolatile(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法 SingletonVolatile()");
    }

    //DCL （Double check Lock 双端检锁机制）
    public static SingletonVolatile getInstance() {
        if (instance == null){
            synchronized (SingletonVolatile.class){
                if (instance == null){
                    instance = new SingletonVolatile();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        //单线程（main线程的操作动作。。。。。。。。。。）
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInst  ance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        //System.out.println();

        for (int i = 1; i <=10; i++) {
            new Thread(() ->{
                SingletonVolatile.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
