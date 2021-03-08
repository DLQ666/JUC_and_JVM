package com.dlq.lockdemo;

import com.dlq.zklockdemo.ZkDistributeLock;
import com.dlq.zklockdemo.ZkLock;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-08 14:38
 */
public class OrderService {

    private OrderUtil orderUtil = new OrderUtil();

    private ZkLock zkLock = new ZkDistributeLock();

    public void getOrderNumber(){
        zkLock.zklock();
        try{
            System.out.println("获得编号 ------>："+orderUtil.getOrderNumber());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            zkLock.zkunlock();
        }

    }
}
