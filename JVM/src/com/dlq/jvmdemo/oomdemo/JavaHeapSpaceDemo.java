package com.dlq.jvmdemo.oomdemo;

import java.util.Random;

/**
 *@program: JavaHeapSpaceDemo
 *@description:
 *@author: Hasee
 *@create: 2021-03-09 22:10
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {

        String str = "dlqaaaa";

        while (true){
            str += str + new Random().nextInt(1111111)+ new Random().nextInt(222222);
            str.intern();
        }
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
