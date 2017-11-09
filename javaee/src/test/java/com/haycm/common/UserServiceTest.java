package com.haycm.common;

import com.haycm.entity.User;
import com.haycm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * author TaoLei
 * date 15-3-12.
 * description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class UserServiceTest {
    @Resource(name = "userService")
    private com.haycm.service.UserService userService;

    private User user;

    @Test
    public void testInsert() {
        /* 测试添加用户 */
        user = new User("admin", "admin", "王洁雪");
        userService.addUser(user);
    }

    @Test
    public void testQuery() {
        /* 测试查询用户 */
        user = new User();
        user.setUsername("aaaa22");
        user = userService.findUserByUserName(user);
        if(user!=null)
            user.show();
    }

    @Test
    public void testUpdate() {
        user=new User();
        user.setUsername("admin");
		/* 测试更新用户 */
        user.setPassword("passaa");
        user.setRealName("admin");
        userService.updateUserByUserName(user);
        if(user!=null)
            user.show();
    }

    @Test
    public void testDelete() {
		/* 测试删除用户 */
        user = new User();
        user.setUsername("admin");
        userService.deleteUserByUserName(user);
    }

    @Test
    public void testQueryForList() {
		/* 测试查询用户列表 */
        List<User> userList = userService.findUserList(2, 3);
        for (User user : userList) {
            user.show();
        }
    }
}
