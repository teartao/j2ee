package com.neotao.testing;

import com.neotao.testing.services.StudentService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SomeServiceInjectTest {

    @BeforeTest
    public void init(){
        MockitoAnnotations.initMocks(SomeServiceInjectTest.class);
    }
    @Mock
    private StudentService studentService;

    @Test
    public void testQueryStudent(){
        studentService.queryStudent(10);
    }
}
