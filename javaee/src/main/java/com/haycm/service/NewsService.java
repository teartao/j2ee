package com.haycm.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.haycm.dao.NewsDao;
import com.haycm.entity.News;
import com.haycm.entity.Page;

@Service(value = "newsService")
public class NewsService {

    private static final Logger logger = Logger.getLogger(NewsService.class);

    @Resource(name = "newsDao")
    private NewsDao newsDao;

    public void addNews(News news) {
        newsDao.insertNews(news);
        logger.info("添加新闻成功......");
    }

    public News findNewsByLongId(News news) {
        if (newsDao.findNewsByLongId(news) != null) {
            logger.info("查询新闻成功......");
            return newsDao.findNewsByLongId(news);
        } else {
            logger.info("查询新闻失败，新闻不存在......");
            return null;
        }
    }

    public List<News> findNewsList(int pageNumber, int pageSize) {
        Page page = new Page(pageNumber, pageSize);
        List<News> newsList = newsDao.findNewsList(page);
        logger.info("查询新闻列表成功......");
        return newsList;
    }

    public void updateNews(News news) {
        if (newsDao.findNewsByLongId(news) != null) {
            newsDao.updateNewsByLongId(news);
            logger.info("编辑新闻成功......");
        } else {
            logger.info("编辑失败，新闻不存在......");
        }
    }

    public void deleteNews(News news) {
        if (newsDao.findNewsByLongId(news) != null) {
            newsDao.deleteNewsByLongId(news);
            logger.info("删除新闻成功......");
        } else {
            logger.info("删除失败，新闻不存在......");
        }
    }

}
