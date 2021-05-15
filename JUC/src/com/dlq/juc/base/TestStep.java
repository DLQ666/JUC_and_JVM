package com.dlq.juc.base;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-04 15:37
 */
public class TestStep {
    public static void main(String[] args) {
        TestStep testStep = new TestStep();
        System.out.println(testStep.f(40));

    }

    //实现f(n)：求n步台阶，一共有几种走法
    public int f(int n){
        if (n<1){
            throw new IllegalArgumentException("n不能小于1");
        }
        if (n==1 || n==2){
            return n;
        }
        return f(n-2)+f(n-1);
    }
}
