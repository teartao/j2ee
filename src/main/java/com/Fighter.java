package com;

/**
 * Created by Administrator on 2015/7/30.
 */
public class Fighter extends Aircraft {
    @Override
    public void takeOff() {
        System.out.println("滑行起飞");
    }

    @Override
    public void fly() {
        System.out.println("超音速飞行");
    }

    @Override
    public void play() {
        System.out.println("战斗");
    }
}
