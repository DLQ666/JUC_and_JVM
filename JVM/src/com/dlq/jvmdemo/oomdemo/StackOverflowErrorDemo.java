package com.dlq.jvmdemo.oomdemo;

/**
 *@program: StackOverflowErrorDemo
 *@description:
 *@author: Hasee
 *@create: 2021-03-09 22:05
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError(); //Exception in thread "main" java.lang.StackOverflowError
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}
