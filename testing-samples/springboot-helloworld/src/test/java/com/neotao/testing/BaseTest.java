package com.neotao.testing;

import com.neotao.testing.services.SomeService;
import com.neotao.testing.services.SomeServiceImpl;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    //最基本的测试，判断方法是否执行
    @Test
    public void runTest() {
        //someMethod()
    }

    //判断方法是否抛出指定异常，抛出指定异常则成功，否则失败
    @Test(expectedExceptions = {IllegalArgumentException.class, NullPointerException.class})
    public void throwExceptions() {
        throw new IllegalArgumentException("参数非法");
    }

    //判断程序运行是否超时 4000<5000 测试运行成功，否则测试运行失败
    @Test(timeOut = 5000)
    public void testThisShouldPass() throws InterruptedException {
        Thread.sleep(4000);
    }

    //判断被测单元是否返回期望结果,此处如果是调用接口，则可以方法中初始化service对象，
    //再通过@BeforeTest在测试方法之前进行前置调用，以达到初始化service的目的
    //这种虽然依赖service，但是被测逻辑单元相对独立，可直接调用
    private SomeService someService;

    //testng注解 testng扩展注解见文末引用
    @BeforeTest
    void setupService() {
        someService = new SomeServiceImpl();
    }

    //判断方法是否返回期望值
    @Test
    public void testSomeService() {
        Map<String, Object> expect = new HashMap<>();
        expect.put("111", "this is testData");

        Map<String, Object> result = someService.getSomeData("111");
        Assert.assertEquals(expect, result);
    }
}
