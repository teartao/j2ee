package com.neotao.thread01;


/**
 * sync 实例方法用法demo
 */
public class SyncDemo02 {
    Object lock;

    public SyncDemo02() {

    }

    /*  public SyncDemo(Object lock){
          this.lock=lock;
      }*/
    public void demo() {
        synchronized (this) { //

        }
    }

    public static void main(String[] args) {

        SyncDemo02 sd = new SyncDemo02();
//        SyncDemo sd2=new SyncDemo();

        new Thread(() -> sd.demo()).start();
        new Thread(() -> sd.demo()).start();
    }
}
