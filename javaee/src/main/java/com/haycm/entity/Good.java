package com.haycm.entity;

import com.utils.NowDateUtil;

public class Good {
	private String longId;// 商品id
	private String name;// 商品名
	private double price;// 商品价格
	private String imageUrl;// 商品图片路径
	private String description;// 商品

	public Good() {
	}

	public Good(String name) {
		this.name = name;
	}

	public Good(String name, double price) {
		this.longId = NowDateUtil.getLongDate();
		this.name = name;
		this.price = price;
	}

	public String getLongId() {
		return longId;
	}

	public void setLongId(String longId) {
		this.longId = longId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void show() {
		System.out.println("商品编号：" + this.longId + "\n\t商品名：" + this.name
				+ "\n\t价格：" + this.price + "\n\t图片路径：" + this.imageUrl
				+ "\n\t商品描述：" + this.description);
	}

}
