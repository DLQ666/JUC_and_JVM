package com.dlq.juc.singletonbase;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-04 12:46
 */
public class SingletonStatic {
    public static final SingletonStatic INSTANCE;

    static {
        INSTANCE = new SingletonStatic();
    }

    private SingletonStatic(){

    }
}
