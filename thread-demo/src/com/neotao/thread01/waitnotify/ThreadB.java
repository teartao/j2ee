package com.neotao.thread01.waitnotify;


public class ThreadB extends Thread {
    private Object lock;


    public ThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("start ThreadB");
            lock.notify(); //唤醒被阻塞的线程
            System.out.println("end ThreadB");
        }
    }
}
