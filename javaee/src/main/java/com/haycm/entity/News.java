package com.haycm.entity;

import com.utils.NowDateUtil;

public class News {
	private String longId;
	private String title;
	private String time;
	private String author;
	private String content;

	public News() {
	}

	public News(String title, String author) {
		this.longId = NowDateUtil.getLongDate();
		this.title = title;
		this.time = NowDateUtil.getY_M_D();
		this.author = author;
	}

	public String getLongId() {
		return longId;
	}

	public void setLongId(String longId) {
		this.longId = longId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void show() {
		System.out.println("标题：" + this.title + ",作者：" + this.author + ",发布时间："
				+ this.time);
	}
}
