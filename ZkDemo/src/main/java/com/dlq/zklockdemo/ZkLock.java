package com.dlq.zklockdemo;

/**
 *@description:
 *@author: Hasee
 *@create: 2021-03-08 14:46
 */
public interface ZkLock {

    void zklock();
    void zkunlock();
}
