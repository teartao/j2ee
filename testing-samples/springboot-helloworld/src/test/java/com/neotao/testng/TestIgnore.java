package com.neotao.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIgnore {

    @Test // default enable=true
    public void test1() {
        Assert.assertEquals(true, true);
    }

    @Test(enabled = true)
    public void test2() {
        Assert.assertEquals(true, true);
    }

    //禁用，不执行
    @Test(enabled = false)
    public void test3() {
        Assert.assertEquals(true, true);
    }

}


