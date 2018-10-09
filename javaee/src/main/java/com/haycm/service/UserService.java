package com.haycm.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.haycm.dao.UserDao;
import com.haycm.entity.Page;
import com.haycm.entity.User;

@Service(value = "userService")
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
	@Resource(name = "userDao")
	private UserDao userDao;

	public void addUser(User user) {
		if (userDao.findUserByUserName(user) == null) {
			userDao.insertUser(user);
            logger.info("添加用户成功......");
		} else {
			logger.info("添加用户失败，用户名已存在......");
		}
	}

	public User findUserByUserName(User user) {
		if (userDao.findUserByUserName(user) != null) {
			logger.info("查询用户成功......");
			return userDao.findUserByUserName(user);
		} else {
			logger.info("查询用户失败，用户不存在......");
			return null;
		}
	}

	public boolean loginCheck(User user) {
		if (userDao.findUserByUserNameAndPassWord(user) != null) {
			logger.info("用户登录成功......");
			return true;
		} else {
			logger.info("登录失败，用户名或密码错误......");
			return false;
		}
	}

	public List<User> findUserList(int pageNumber, int pageSize) {
		Page page = new Page(pageNumber, pageSize);
		List<User> userList = userDao.findUserList(page);
		logger.info("查询用户列表成功......");
		return userList;
	}

	public void updateUserByUserName(User user) {
		if (userDao.findUserByUserName(user) != null) {
            user.setLongId(userDao.findUserByUserName(user).getLongId());
			userDao.updateUserByUserName(user);
			logger.info("修改用户成功......");
		} else {
			logger.info("修改失败，用户不存在......");
		}
	}

	public void deleteUserByUserName(User user) {
		if (userDao.findUserByUserName(user) != null) {
			userDao.deleteUserByUserName(user);
			logger.info("删除用户成功......");
		} else {
			logger.info("删除失败，用户不存在......");
		}
	}

}
