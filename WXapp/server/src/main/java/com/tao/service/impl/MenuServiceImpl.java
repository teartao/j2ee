package com.tao.service.impl;

import com.tao.dao.UserDao;
import com.tao.dto.Menu;
import com.tao.dto.MenuSelection;
import com.tao.dto.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.tao.service.MenuService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author neotao
 * @Date 2018/5/18
 * @Version V0.0.1
 * @Desc
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    private UserDao userDao;

    @Override
    public Menu publishMenu(Menu menu) {
        User user = userDao.findOne(new User());
        if (user == null) {
            userDao.add(menu.getCreater());
        } else {

        }

        List<MenuSelection> selections = new ArrayList();

        MenuSelection selection = new MenuSelection();
        selection.setName("土豆烧鸡");
        selection.setPrice(12);
        selections.add(selection);
        menu.setSelectionList(selections);

        String sql = "insert into menu(`title`,`create_time`,`creater_id`) values(?,?,?)";
        jdbcTemplate.update(sql,
                new Object[]{menu.getTitle(), menu.getCreateTime(), menu.getCreaterId()});
        return null;
    }

    @Override
    public Menu latestMenu(Menu menu) {
        return null;
    }
}
