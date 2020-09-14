package com.neotao.thread01;

/**
 * 常见错误用法
 */
public class ErrorLock {
    static final Object lock = new Object();
    static final ErrorLock lock2 = new ErrorLock();

    public static void main(String[] args) {
        errorLock1();
//        errorLock2();
    }

    public static void errorLock1(){
        synchronized (lock) {
            //没有thread的情况下，lock没有意义
        }
    }

    /**
     * 锁对象不一样，没有线程竞争
     */
    public static void errorLock2() {
        new Thread(() -> {
            synchronized (ErrorLock.class) {

            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {

            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {

            }
        }).start();
    }
}
