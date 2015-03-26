package com.haycm.mockito;

import com.haycm.entity.User;
import com.haycm.service.NewsService;
import com.haycm.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.mockito.Mockito.*;

/**
 * author TaoLei
 * date 15-3-18.
 * description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class PractiseNewsServiceTest {
    @Resource
    private NewsService newsService;

    @Test
    public void testInsert(){

    }
}
