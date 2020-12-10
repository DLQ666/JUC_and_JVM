package com.dlq.juc;

/**
 * @program: JUC_JVM
 * @description: 复习lambda表达式
 * @author: Hasee
 * @create: 2020-07-21 15:09
 *
 * 1、函数式编程
 *     int age = 23;
 *
 *     y = x + 1；
 *
 *     f(x) = kx + 1；
 *
 *  1、拷贝中括号，写死右箭头，落地大括号
 *  2、@FunctionalInterface
 *  3、default  可以有多个
 *  4、static  可以有多个
 *
 *  Lambda表达式 解决了匿名内部类代码冗余的一个语法现象
 */
@FunctionalInterface
interface Foo{
//    public void sayHello();
    public int add(int x, int y);

    //3、default
    default int mul(int x, int y){
        return x * y;
    }
    default int mul2(int x, int y){
        return x * y;
    }

    //4、static
    static int div(int x, int y) {
        return x / y;
    }
    static int div2(int x, int y) {
        return x / y;
    }
}

public class LambdaExpressDemo02 {
    public static void main(String[] args) {

        /*Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("********hello 1414");
            }

            @Override
            public int add(int x, int y) {
                return 0;
            }
        };
        foo.sayHello();*/

        Foo foo = (int x, int y) -> {
            System.out.println("come in add method");
            return x + y ;
        };
        System.out.println(foo.add(3,5));

        System.out.println(foo.mul2(3,5));

        System.out.println(Foo.div2(10,2));
    }
}
