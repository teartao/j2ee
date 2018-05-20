package com.tao.dao;

import com.tao.dto.User;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class UserDao extends BaseDao<User> {
    @Override
    protected String getTableName() {
        return "user";
    }

    @Override
    protected JSONObject wrapToJson(User user) {
        JSONObject json = new JSONObject();
        json.put("id", user.getId());
        json.put("name", user.getName());
        return json;
    }

    @Override
    protected User wrapToObject(JSONObject columns) {
        User user = new User();
        user.setId(columns.getLong("id"));
        user.setName(columns.getString("name"));
        return null;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        userDao.findOne(new User());
    }
}
