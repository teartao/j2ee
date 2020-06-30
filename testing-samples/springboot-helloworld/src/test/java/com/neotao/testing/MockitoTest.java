package com.neotao.testing;

//Let's import Mockito statically so that the code looks clearer

import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoTest {

    @Test
    public void mockitoBehaviour() {
        //mock creation
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verification
        verify(mockedList).add("one");

        verify(mockedList).clear();

    }

    @Test
    public void mockitoStubbingAndArgumentMatchers() {
        /*
         * https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#stubbing
         */
        //You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        //following prints "first"
        System.out.println(mockedList.get(0));

        //following throws runtime exception
        // System.out.println(mockedList.get(1));//todo

        //following prints "null" because get(999) was not stubbed
        // System.out.println(mockedList.get(999));//todo

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed.
        verify(mockedList).get(0);


        /*
         * Argument Matchers
         * https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html#argument_matchers
         */
        //stubbing using built-in anyInt() argument matcher
        when(mockedList.get(anyInt())).thenReturn("element");

        //stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
        // when(mockedList.contains(argThat(isValid()))).thenReturn(true);//todo

        //following prints "element"
        System.out.println(mockedList.get(999));

        //you can also verify using an argument matcher
        verify(mockedList).get(anyInt());

        //argument matchers can also be written as Java 8 Lambdas
        // verify(mockedList).add(argThat(someString -> someString.length() > 5));//todo


    }
}
