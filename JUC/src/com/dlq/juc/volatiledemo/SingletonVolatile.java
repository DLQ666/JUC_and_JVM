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

    }

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
}
