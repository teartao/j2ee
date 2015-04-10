package com.haycm.controller;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.ActionEnter;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
public class IndexController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "index";
    }

    @RequestMapping(value = "editorServer", method = RequestMethod.GET)
    public void editorServer(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            request.setCharacterEncoding("utf-8");
            response.setHeader("Content-Type", "text/html");
            out = response.getWriter();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String path = request.getContextPath();
        String rootPath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";

        out.write(new ActionEnter(request, rootPath).exec());
    }

    @ResponseBody
    @RequestMapping(value = "getJson", produces = "text/plain;charset=UTF-8")
    public String getJson() {
        JSONObject studentList = new JSONObject();

        JSONObject student1 = new JSONObject();
        student1.put("name", "Jack1");
        student1.put("age", "11");
        student1.put("number", "num1111");

        JSONObject student2 = new JSONObject();
        student2.put("name", "Tom2");
        student2.put("age", "22");
        student2.put("number", "num222");

        JSONObject student3 = new JSONObject();
        student3.put("name", "Jon");
        student3.put("age", "33");
        student3.put("number", "num333");

        studentList.put("studen t1", student1);
        studentList.put("studen t2", student2);
        studentList.put("studen t3", student3);
        System.out.println(studentList);

        return studentList.toJSONString();
    }
}
