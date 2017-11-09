package com.haycm.dao;

import java.util.List;

import com.haycm.entity.Page;
import com.haycm.entity.User;

public interface UserDao {
	public void insertUser(User user);

	public User findUserByUserName(User user);

    public void updateUserByUserName(User user);

    public void deleteUserByUserName(User user);

	public List<User> findUserList(Page page);

	public User findUserByUserNameAndPassWord(User user);
}
