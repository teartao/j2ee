package com.haycm.entity;

public class Page {
	public int pageSize = -1;
	public int pageNumber = 1;

	public int startNum = 1;

	public Page(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.startNum = (pageNumber - 1) * pageSize;
	}

}
