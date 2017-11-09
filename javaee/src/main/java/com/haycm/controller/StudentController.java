package com.haycm.controller;

import com.alibaba.fastjson.JSONObject;
import com.haycm.entity.Person;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taolei on 2017/8/15.
 */
@Controller
@RequestMapping(value = "student")
public class StudentController {
    private static final Logger logger = Logger.getLogger(StudentController.class);

    @ResponseBody
    @RequestMapping(value = "view/{studentId}", method = RequestMethod.GET)
    public Person getStudent(@PathVariable("studentId") int studentId) {
        logger.info("获取学生信息id = " + studentId);
        Person person = new Person();
        person.setName("张三");
        person.setSex("男");
        person.setAge(30);
        person.setId(studentId);
        return person;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<JSONObject> studentList() {
        List<JSONObject> students = new ArrayList<>();
        JSONObject student = new JSONObject();
        student.put("id","001");
        student.put("name","luXX");
        student.put("age","25");
        student.put("birth","1992-07-06");
        student.put("photo","https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
        student.put("sex","1");
        students.add(student);
        return students;
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public JSONObject editStudent(@RequestBody JSONObject student) {
        return student;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONObject addStudent(@RequestBody JSONObject student) {
        return student;
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONObject deleteStudent(@RequestBody JSONObject student) {
        student.put("status","fail");
        return student;
    }
}
