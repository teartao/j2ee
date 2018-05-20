package com.tao.dao;

import org.apache.ibatis.annotations.Param;
import net.hehe.common.Page;

import java.util.List;

public interface BaseDao<T> {
    void add(@Param("entity") T param);

    List<T> findList(@Param("entity") T param ,@Param("page") Page page);

    T find(@Param("entity") T param);

    Integer update(@Param("entity") T param);

    Integer delete(@Param("entity") T param);

    public T selectById(Object id);

    public Integer updateBySelect(@Param("entity") T t);

    public Long queryCount(@Param("entity") T entity);

}
