package com.dlq.jvmdemo;

import java.util.Random;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-07 14:57
 */
public class JVMOOMDemo {

    public static void main(String[] args) {
        String str = "www.atguigu.com" ;
        while(true)
        {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999) ;
        }
    }
}
