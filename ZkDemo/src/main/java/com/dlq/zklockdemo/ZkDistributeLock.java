package com.dlq.zklockdemo;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;

/**
 *@program: JUC_JVM
 *@description:
 *@author: Hasee
 *@create: 2021-03-08 15:13
 */
public class ZkDistributeLock extends ZkAbstractTemplateLock {

    @Override
    public boolean tryZkLock() {
        try {
            zkClient.createEphemeral(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void waitZkLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                if (countDownLatch != null){
                    countDownLatch.countDown();
                }
            }
        };
        zkClient.subscribeDataChanges(path,iZkDataListener);

        if (zkClient.exists(path)){
            // 只能等着，不能往下走
            countDownLatch = new CountDownLatch(1);

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        zkClient.unsubscribeDataChanges(path,iZkDataListener);
    }

}
