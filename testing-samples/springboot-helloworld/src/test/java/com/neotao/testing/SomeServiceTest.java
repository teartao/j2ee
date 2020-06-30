package com.neotao.testing;

import com.neotao.testing.services.SomeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest//(classes = TestingApplication.class)
public class SomeServiceTest {

    @Autowired
    private SomeService someService;

    @Test
    public void testSomeService(){
        Map<String,Object> expect = new HashMap<>();
        expect.put("111","this is testData");

        Map<String,Object>  result = someService.getSomeData("111");
        Assert.assertEquals(expect,result);
    }
}
