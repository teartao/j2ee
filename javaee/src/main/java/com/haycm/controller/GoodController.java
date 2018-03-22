package com.haycm.controller;

import com.alibaba.fastjson.JSONObject;
import com.haycm.entity.Good;
import com.haycm.service.GoodService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("good")
public class GoodController {
    @Resource(name = "goodService")
    private GoodService goodService;

    @RequestMapping("/addGood")
    public Good addGood(@RequestParam(value = "name") String name,
                        @RequestParam(value = "price") double price) {
        /* 测试添加商品 */
        Good good = new Good(name, price);
        good.setImageUrl("../css/image/logo.png");
        good.setDescription("福满多");
        goodService.addGood(good);
        return good;
    }

    @RequestMapping("/findGood")
    public Good findGood() {
        /* 测试查询商品 */
        Good good = goodService.findGoodByName("阳春面");
        good.show();
        return good;
    }

    @RequestMapping("/editGood")
    public Good editGood(@RequestParam(value = "name") String name,
                         @RequestParam(value = "price") double price) {
        /* 测试修改商品 */
        Good good = new Good();
        good.setName(name);
        good.setPrice(price);
        good.setImageUrl("../image/logo222.png");
        good.setDescription("福多多222");

        goodService.updateGood(good);
        return good;
    }

    @RequestMapping("/deleteGood")
    public JSONObject deleteGood(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "price") double price, HttpServletRequest request, HttpSession session) {
        /* 测试删除商品 */
        goodService.deleteGoodByName(name);
        return new JSONObject();
    }
}
