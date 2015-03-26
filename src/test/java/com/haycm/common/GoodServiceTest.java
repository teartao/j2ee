package com.haycm.common;

import com.haycm.entity.Good;
import com.haycm.service.GoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import javax.annotation.Resource;
import java.util.List;

/**
 * author TaoLei
 * date 15-3-12.
 * description
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class GoodServiceTest extends AbstractTestExecutionListener {

    @Autowired
    private com.haycm.service.GoodService goodService;

    private Good good ;
    @Test
    public void testInsert() {
        /* 测试添加商品 */
        Good good = new Good("name-11", 12.1);
        good.setImageUrl("../css/image/logo.png");
        good.setDescription("福满多");
        goodService.addGood(good);
    }


    @Test
    public void testQuery() {
        /* 测试查询商品 */
        good = goodService.findGoodByName("红烧牛肉面");
        if(good!=null)
            good.show();
    }

    @Test
    public void testUpdate() {
        good = new Good();
		/* 测试修改商品 */
        good.setName("福多多");
        good.setPrice(99.9);
        good.setImageUrl("../image/logo222.png");
        good.setDescription("福多多222");

        goodService.updateGood(good);
    }

    @Test
    public void testDelete() {

		/* 测试删除商品 */
        good = new Good();
        goodService.deleteGoodByName("红烧牛肉面2233");
    }

    @Test
    public void testQueryForList() {
		/* 测试查询商品列表 */
        List<Good> goodList = goodService.findGoodsList(2, 5);
        for (Good good : goodList) {
            good.show();
        }
    }
}
