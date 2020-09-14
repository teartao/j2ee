package com.neotao;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestFinger {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //第一个子线程执行
        for (int i = 20; i > 0; i--) {
            final int index = i;
            //传入线程执行参数
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        tryFinger(index);
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public static void tryFinger(int i) throws ExecutionException, InterruptedException {
        FingerThread callableThread = new FingerThread();

        //通过Feature启动,通过泛型接收返回值
        FutureTask<List<String>> threadFeature = new FutureTask<>(callableThread);
        threadFeature.run();

        //通过isDone 判断线程是否执行结束
        // threadFeature.isDone();

        //通过get拿到线程中返回数据
        List<String> threadReturnResult = threadFeature.get();
        System.out.println(i + " : " + threadReturnResult);
    }


}
