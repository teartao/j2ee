package com.neotao.thread02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionWait implements Runnable {

    private Lock lock;
    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
//        synchronized (lock) {
        try {
            lock.lock(); //竞争锁
            System.out.println("begin - ConditionWait");
            condition.await();//阻塞(1. 释放锁, 2.阻塞当前线程, FIFO（单向、双向）)
            System.out.println("end - ConditionWait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }
//        }


    }


}
