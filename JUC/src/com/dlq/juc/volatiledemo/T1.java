package com.dlq.juc.volatiledemo;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-05 12:31
 */
public class T1 {

    volatile int n = 0;

    public void addTo60(){
        n++;
    }
}
