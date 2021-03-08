package com.dlq.lockdemo;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-08 14:36
 */
public class OrderUtil {

    private static int number = 0;

    public String getOrderNumber(){

        return "\t 生成订单号："+ (++number);
    }
}
