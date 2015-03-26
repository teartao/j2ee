package com.haycm.mockito;

import static org.mockito.Mockito.*;
//静态引入可以在调用静态方法时无需写上类名,大量调用静态方法时可以节约时间

import com.haycm.entity.User;
import com.haycm.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * author TaoLei
 * date 15-3-18.
 * description
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class ExampleTest {

    @Test
    public void mockUser() {
        //创建mock对象，参数可以是类，也可以是接口
        User user = mock(User.class);

        //设置方法的预期返回值
        when(user.getUsername()).thenReturn("admin");

        String result = user.getUsername();

        //验证方法调用(是否调用了getUsername())
        verify(user).getUsername();

        //junit测试
        Assert.assertEquals("admin", result);
    }

    @Test
    public void stubUser() {
        //创建mock对象，参数可以是类，也可以是接口
        User user = mock(User.class);

        //（另一种方法）设置方法的预期返回值
        doReturn("admin1").when(user).getUsername();

        String result = user.getUsername();

        //验证方法调用(是否调用了getUsername())
        verify(user).getUsername();

        //junit测试
        Assert.assertEquals("admin", result);
    }

    @Test
    public void voidUser() {
        User user = new User("admin", "admin", "陶呵呵");
        //创建mock对象，参数可以是类，也可以是接口
        UserService userService = mock(UserService.class);

        //没有返回值的void方法与其设定(支持迭代风格，第一次调用donothing,第二次dothrow抛出runtime异常)
        doNothing().doThrow(new RuntimeException("void exception")).when(userService).addUser(user);

        userService.addUser(user);//调用addUser()  可以调用多次，在下面times(n) 中以n来表示

        //验证方法调用(是否调用了addUser()) times(1)表示调用了1次，如果次数不正确，测试失败
        verify(userService, times(1)).addUser(user);

        //这里没有junit测试：assert 因为方法返回值为void类型，只需验证是否调用方法即可
    }

    @Test
    public void argumentMatcherTest() {

        List<String> list = mock(List.class);

        when(list.get(anyInt())).thenReturn("Hello", "World");

        String result = list.get(0) + list.get(1);

        verify(list, times(2)).get(anyInt());

        Assert.assertEquals("HelloWorld", result);

    }

    @Test
    public void argumentMatcherTest2() {
        Map<Integer, String> map = mock(Map.class);
        when(map.put(anyInt(), anyString())).thenReturn("hello");//anyString()替换成"hello"就会报错
        map.put(1, "world");
        verify(map).put(eq(1), eq("world")); //eq("world")替换成"world"也会报错
    }

    @Test
    public void verifyInvocate() {

        List<String> mockedList = mock(List.class);
        //using mock
        mockedList.add("once");
        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        /**
         * 基本的验证方法
         * verify方法验证mock对象是否有没有调用mockedList.add("once")方法
         * 不关心其是否有返回值，如果没有调用测试失败。
         */
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");//默认调用一次,times(1)可以省略


        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        //never()等同于time(0),一次也没有调用
        verify(mockedList, times(0)).add("never happened");

        //atLeastOnece/atLeast()/atMost()
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("twice");
        verify(mockedList, atMost(5)).add("three times");
    }
}
