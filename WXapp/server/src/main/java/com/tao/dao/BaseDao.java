package com.tao.dao;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class BaseDao<T> {

    protected abstract String getTableName();

    protected abstract JSONObject wrapToJson(T entity);

    protected abstract T wrapToObject(JSONObject columns);

    public T findOne(T param) {
        JSONObject columnsJSON = wrapToJson(param);

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM ").append(getTableName()).append(" WHERE ");
        for (Iterator<String> it = columnsJSON.keySet().iterator(); it.hasNext(); ) {
            String columnName = it.next();
            sqlBuilder.append(columnName).append("=").append(columnsJSON.get(columnName));
            if (it.hasNext()) {
                sqlBuilder.append(" AND ");
            }
        }

        System.out.println(sqlBuilder.toString());
        return wrapToObject(columnsJSON);
    }

    public List<T> findList(T param) {
        return new ArrayList<T>();
    }

    public int add(T param) {
        if (param != null) {
            return 1;
        }
        return -1;
    }
}
