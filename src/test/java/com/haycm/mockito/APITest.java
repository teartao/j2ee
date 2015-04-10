package com.haycm.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;

//Let's import Mockito statically so that the code looks clearer
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * @Author TaoLei
 * @Date 2015/4/7.
 * this is a test for mockito API:
 * http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class APITest {
    /**
     * 1. Let's verify some behaviour!
     */
    @Test
    public void someBehavior() {
        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    /**
     * 2.How about some stubbing?
     */
    @Test
    public void someStubbing() {
        //You can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        System.out.println(mockedList.get(0));

        //following throws runtime exception
        System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
        //If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
        verify(mockedList).get(0);
    }

    /**
     * 3. Argument matchers
     */
    @Test
    public void argumentMatchers() {
       /*
       LinkedList mockedList = mock(LinkedList.class);

        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //stubbing using hamcrest (let's say isValid() returns your own hamcrest matcher):
        when(mockedList.contains(argThat(isValid()))).thenReturn("element");

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());
        */
    }

}
