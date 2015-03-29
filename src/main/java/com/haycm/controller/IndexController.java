package com.haycm.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @ResponseBody
    @RequestMapping(value="/index",produces = "text/plain;charset=UTF-8")
    public String welcome() {
        JSONObject studentList=new JSONObject();

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

        studentList.put("student1",student1);
        studentList.put("student2",student2);
        studentList.put("student3",student3);
        System.out.println(studentList);

        return studentList.toJSONString();
    }
}
