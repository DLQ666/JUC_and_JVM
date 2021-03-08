package com.dlq.zklockdemo;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-08 14:47
 */
public abstract class ZkAbstractTemplateLock implements ZkLock {

    public static final String ZKSERVER = "192.168.142.129:2181";
    public static final int TIME_OUT = 45 * 1000;
    ZkClient zkClient = new ZkClient(ZKSERVER,TIME_OUT);

    protected String path = "/zklockdemo";
    protected CountDownLatch countDownLatch = null;


    @Override
    public void zklock() {
        if (tryZkLock()){
            System.out.println(Thread.currentThread().getName()+"\t 占用锁成功！");
        }else {
            waitZkLock();

            zklock();
        }
    }

    public abstract void waitZkLock();

    public abstract boolean tryZkLock();

    @Override
    public void zkunlock() {
        if (zkClient != null){
            zkClient.close();
        }
        System.out.println(Thread.currentThread().getName()+"\t 释放锁成功！！！");

        System.out.println();
        System.out.println();
    }
}
