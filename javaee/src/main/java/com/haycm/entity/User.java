package com.haycm.entity;

import com.utils.NowDateUtil;

public class User {
	private String longId;// 用户id
	private String username;// 登录名
	private String password;// 用户密码
	private String realName;// 用户姓名

	public User() {
	}

	public User(String username, String password, String realName) {
		this.longId = NowDateUtil.getLongDate();
		this.username = username;
		this.password = password;
		this.realName = realName;
	}

	public String getLongId() {
		return longId;
	}

	public void setLongId(String longId) {
		this.longId = longId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void show() {
		System.out.println("UserId : " + this.longId + "\n\tusername："
				+ this.username + "\n\tpassword：" + this.password
				+ "\n\trealName：" + this.realName);
	}
}
