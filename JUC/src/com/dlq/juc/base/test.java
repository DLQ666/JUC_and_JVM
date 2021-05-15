package com.dlq.juc.base;

import com.dlq.juc.singletonbase.SingletonEnum;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-03 22:53
 */
public class test {

    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i*i++;
        System.out.println(i);
        System.out.println(j);
        System.out.println(k);

        SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
    }

}
