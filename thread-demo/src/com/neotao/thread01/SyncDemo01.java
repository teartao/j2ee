package com.neotao.thread01;

/**
 * synchronized 用法
 */
public class SyncDemo01 {


    private static int count = 0;//正确性

    public synchronized static void incrMethodSync() {
        incr();
        //其它工作
    }

    public static void incrClassSync() {
        synchronized (SyncDemo01.class) {
            incr();
        }
        //其它工作
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                SyncDemo01.incr();//最终结果可能错误
//                AtomicDemo.incrClassSync();//对类加锁，锁定当前类对象
//                AtomicDemo.incrMethodSync();
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("运行结果：" + count);

    }

    public static void incr() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

}
