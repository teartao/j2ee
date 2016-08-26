package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author TaoLei
 * @Date 16/7/20
 * @Since
 * @Desc this is a test controller
 */
@Controller
public class IndexController {
    @Autowired
    @Qualifier("guestServiceImpl")
    private GuestService guestService;

    @ResponseBody
    @RequestMapping(value = "guests", method = RequestMethod.GET)
    public JSONObject getGuests(HttpServletRequest request) {
        String keyword = request.getParameter("keyword");
        JSONObject result = new JSONObject();
        result.put("guests", guestService.findGuests(keyword));
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "addMoney", method = RequestMethod.POST)
    public JSONObject addMoney(@RequestBody JSONObject param) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        System.out.println(df.format(new Date()) + " -- " + param.getString("name") + " -- " + param.getString("price"));
        return param;
    }
}
