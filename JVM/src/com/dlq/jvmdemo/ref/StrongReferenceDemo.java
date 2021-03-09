package com.dlq.jvmdemo.ref;

/**
 *@program: StrongReferenceDemo
 *@description:
 *@author: Hasee
 *@create: 2021-03-09 16:58
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object obj1 = new Object();//这样定义的默认就是强引用
        Object obj2 = obj1;//obj2引用赋值
        obj1 = null;//置空

        System.gc();
        System.out.println(obj2);
    }
}
