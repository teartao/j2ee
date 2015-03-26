package com.haycm.common;

import com.haycm.entity.News;
import com.haycm.service.NewsService;
import com.haycm.util.NowDateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * author TaoLei
 * date 15-3-12.
 * description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class NewsServiceTest {
    @Resource(name = "newsService")
    private com.haycm.service.NewsService newsService;

    private News news;

    @Test
    public void testInsert() {
        /* 测试添加新闻 */
        news = new News("news111", "admin");
        news.setContent("啊啊啊啊");
        newsService.addNews(news);
    }

    @Test
    public void testQuery() {
        /* 测试查询新闻 */
        news = new News();
        news.setLongId("3445643645645");
        news = newsService.findNewsByLongId(news);
        news.show();
    }

    @Test
    public void testUpdate() {
        /* 测试更新新闻 */
        news = new News();
        news.setLongId("3445643645645");

        news.setAuthor("asd");
        news.setContent("啊啊啊啊");
        news.setTime(NowDateUtil.getY_M_D());
        news.setTitle("阿斯达斯");

        newsService.updateNews(news);
        news.show();
    }

    @Test
    public void testDelete() {
		/* 测试删除新闻 */
        News news = new News();
        news.setLongId("1406114404012");
        newsService.deleteNews(news);
    }

    @Test
    public void testsQueryForList() {
		/* 测试查询新闻列表 */
        List<News> newsList = newsService.findNewsList(2, 3);
        for (News news : newsList) {
            news.show();
        }
    }
}
