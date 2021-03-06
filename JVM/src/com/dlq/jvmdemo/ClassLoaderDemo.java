package com.dlq.jvmdemo;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-06 13:56
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader().getParent().getParent());
        System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader());
    }
}
