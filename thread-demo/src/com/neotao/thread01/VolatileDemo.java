package com.neotao.thread01;

public class VolatileDemo {
    public volatile static boolean stop = false;

    public static void main(String[] args) throws
            InterruptedException {
        Thread thread = new Thread(() -> {
            while (!stop) {
                int i = 0;
                i++;
            }
        });
        thread.start();
        System.out.println("begin start thread");
        Thread.sleep(1000);

        new Thread(() -> {
            stop = true;//改为true后理应停止循环，但thread未能及时读到
        }).start();
    }


}
