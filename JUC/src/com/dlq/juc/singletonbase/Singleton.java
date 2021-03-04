package com.dlq.juc.singletonbase;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-04 11:57
 */
public class Singleton {

    public static final Singleton INSTANCE = new Singleton();

    private Singleton(){

    }

}
