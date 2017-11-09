package com.architecture;

/**
 * Created by Administrator on 2015/7/30.
 */
public class Helicopter extends Aircraft {
    @Override
    public void takeOff() {
        System.out.println("垂直起飞");
    }

    @Override
    public void fly() {
        System.out.println("音速飞行");
    }

    @Override
    public void play() {
        System.out.println("战场营救");
    }
}
