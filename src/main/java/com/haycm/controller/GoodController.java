package com.haycm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.haycm.entity.Good;
import com.haycm.service.GoodService;

@Controller
public class GoodController {
	@Resource(name = "goodService")
	private GoodService goodService;

	private Good good;

	@RequestMapping("/goodInfo")
	public String findGoodList() {
		return "index";
	}

	@RequestMapping("/manage/addGood")
	public String addGood(@RequestParam(value = "name") String name,
			@RequestParam(value = "price") double price) {
		/* 测试添加商品 */
		good = new Good(name, price);
		good.setImageUrl("../css/image/logo.png");
		good.setDescription("福满多");
		goodService.addGood(good);
		return "index";
	}

	@RequestMapping("/manage/findGood")
	public String findGood(@RequestParam(value = "name") String name,
			@RequestParam(value = "price") double price) {
		/* 测试查询商品 */
		good = goodService.findGoodByName(name);
		good.show();
		return "index";
	}

	@RequestMapping("/manage/editGood")
	public String editGood(@RequestParam(value = "name") String name,
			@RequestParam(value = "price") double price) {
		/* 测试修改商品 */
		good.setName(name);
		good.setPrice(price);
		good.setImageUrl("../image/logo222.png");
		good.setDescription("福多多222");

		goodService.updateGood(good);
		return "index";
	}

	@RequestMapping("/manage/deleteGood")
	public String deleteGood(@RequestParam(value = "name") String name,
							 @RequestParam(value = "price") double price, HttpServletRequest request, HttpSession session) {
		/* 测试删除商品 */
		goodService.deleteGoodByName(name);
		return "index";
	}
}
