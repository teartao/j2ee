package com.haycm.dao;

import java.util.List;

import com.haycm.entity.Good;
import com.haycm.entity.Page;

public interface GoodDao {
	public void insertGood(Good good);

	public List<Good> findGoodsList(Page page);

	public Good findGoodByName(Good good);

	public void updateGoodById(Good good);

	public void deleteGoodByName(Good good);

	public Object findGoodById(Good good);

}
