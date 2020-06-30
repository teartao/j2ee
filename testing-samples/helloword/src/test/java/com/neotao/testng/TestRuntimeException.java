package com.neotao.testng;

import org.testng.annotations.Test;

public class TestRuntimeException {
    /**
     * 方法内部出现 {@link java.lang.ArithmeticException}则运行通过
     */
    @Test(expectedExceptions = ArithmeticException.class)
    public void divisionWithException() {
        int i = 1 / 0;
        System.out.println("After division the value of i is :"+ i);
    }
}
