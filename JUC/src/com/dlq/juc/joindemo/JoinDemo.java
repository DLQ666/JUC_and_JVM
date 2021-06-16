package com.dlq.juc.joindemo;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-06-15 16:20
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 =  new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    Thread.sleep(3000);
                }catch(Exception e){
                    e.printStackTrace();
                }
                System.out.println("222222222222");
            }
        });
        t1.start();
        t1.join();
        // 这行代码必须要等t1全部执行完毕，main才会执行这行
        System.out.println("1111");
    }
}
