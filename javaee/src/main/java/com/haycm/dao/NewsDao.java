package com.haycm.dao;

import java.util.List;

import com.haycm.entity.News;
import com.haycm.entity.Page;

public interface NewsDao {
	public void insertNews(News news);

	public List<News> findNewsList(Page page);

	public News findNewsByLongId(News news);

	public void updateNewsByLongId(News news);

	public void deleteNewsByLongId(News news);
}
