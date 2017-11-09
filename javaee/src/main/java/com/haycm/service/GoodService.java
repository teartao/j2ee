package com.haycm.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.haycm.dao.GoodDao;
import com.haycm.entity.Good;
import com.haycm.entity.Page;

@Service(value = "goodService")
public class GoodService {

    private static final Logger logger = Logger.getLogger(GoodService.class);

    @Resource(name = "goodDao")
    private GoodDao goodDao;

    private Good good;

    public void addGood(Good good) {
        if (goodDao.findGoodByName(good) == null) {
            goodDao.insertGood(good);
            logger.info("商品添加成功......");
        } else {
            logger.info("商品：" + good.getName() + " 添加失败,商品已存在");
        }
    }

    public Good findGoodByName(String name) {
        good = new Good();
        good.setName(name);
        if (goodDao.findGoodByName(good) != null) {
            logger.info("商品查询成功......");
            return goodDao.findGoodByName(good);
        } else {
            logger.info("查询： " + good.getName() + " 失败，商品不存在");
            return null;
        }
    }

    public List<Good> findGoodsList(int pageNumber, int pageSize) {
        Page page = new Page(pageNumber, pageSize);
        logger.info("商品列表查询成功......");
        return goodDao.findGoodsList(page);
    }

    public void updateGood(Good good) {
        if (goodDao.findGoodById(good) != null) {
            goodDao.updateGoodById(good);
            logger.info("商品信息修改成功......");
        } else {
            logger.info("商品：" + good.getName() + " 信息修改失败，商品不存在");
        }
    }

    public void deleteGoodByName(String name) {
        good = new Good();
        good.setName(name);
        if (goodDao.findGoodByName(good) != null) {
            goodDao.deleteGoodByName(good);
            logger.info("商品删除成功......");
        } else {
            logger.info("商品：" + good.getName() + "删除失败，商品不存在");
        }
    }

}
