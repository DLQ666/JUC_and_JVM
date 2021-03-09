package com.dlq.jvmdemo.ref;

import java.lang.ref.WeakReference;

/**
 *@program: WeakReferenceDemo
 *@description:
 *@author: Hasee
 *@create: 2021-03-09 17:33
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(weakReference.get());

        o1 = null;
        System.gc();
        System.out.println("=======================");

        System.out.println(o1);
        System.out.println(weakReference.get());
    }
}
