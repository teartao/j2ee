package com.neotao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class FingerThread implements Callable {

    volatile List<String> fingerPrints = new ArrayList<>();
    final CountDownLatch latch = new CountDownLatch(3);

    @Override
    public List<String> call() throws Exception {
        try {
            while (latch.getCount() > 0) {
                Thread.sleep((int) (Math.random() * 1000));
                int randomFinger = (int) (Math.random() * 1000);
                if (randomFinger > 100) {
                    fingerPrints.add(randomFinger + "");
                    latch.countDown();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return fingerPrints;
    }
}
