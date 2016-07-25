package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.entity.Guest;
import com.tao.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author TaoLei
 * @Date 16/7/20
 * @Since
 * @Desc
 */
@Controller
public class IndexController {
    @Autowired
    @Qualifier("guestServiceImpl")
    private GuestService guestService;

    @ResponseBody
    @RequestMapping(value = "guests", method = RequestMethod.GET)
    public JSONObject getException(HttpServletRequest request) throws IOException {
        String keyword = request.getParameter("keyword");
        JSONObject result = new JSONObject();
        result.put("guests", guestService.findGuests(keyword));
        return result;
    }
}
