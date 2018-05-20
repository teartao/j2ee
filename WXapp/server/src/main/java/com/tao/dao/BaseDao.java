package com.tao.dao;

import java.util.List;

public interface BaseDao<T> {
    void add(T param);

    List<T> findList(T param);

    T find(T param);

    void update(T param);

    void delete(T param);
}
