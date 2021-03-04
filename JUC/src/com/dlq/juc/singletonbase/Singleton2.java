package com.dlq.juc.singletonbase;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-04 13:10
 */
public class Singleton2 {

    private static volatile Singleton2 instance;

    private Singleton2(){

    }

    public static Singleton2 getInstance() {
        if (instance == null){
            synchronized (Singleton2.class){
                if (instance == null){
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
