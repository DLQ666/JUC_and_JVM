package com.dlq.lockdemo;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-08 14:39
 */
public class Client {

    public static void main(String[] args) {

        OrderService orderService = new OrderService();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                new OrderService().getOrderNumber();
                System.out.println();
            }, String.valueOf(i)).start();
        }
    }
}
