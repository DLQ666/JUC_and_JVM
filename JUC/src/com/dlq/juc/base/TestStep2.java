package com.dlq.juc.base;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-04 15:51
 */
public class TestStep2 {

    public static void main(String[] args) {
        TestStep2 testStep2 = new TestStep2();
        System.out.println(testStep2.loop(10000));
    }

    public long loop(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }

        long two = 1; //初始化为走到还有二级台阶的走法
        long one = 2; //初始化为走到还有一级台阶的走法
        long sum = 0; //全部走法-3级台阶开始使用

        for (long i=3 ;i<=n;i++){
            sum = one + two;
            two = one; //第四级台阶的剩两级台阶走法就等于三级台阶的剩余一级台阶的走法
            one = sum; //第四级台阶的剩一级台阶的走法就等于三级台阶的全部走法
                       //以此类推 5 -6 -7 个是上一个剩余台阶走法
        }
        return sum;
    }
}
