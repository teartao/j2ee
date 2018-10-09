package com.haycm.controller;

import com.alibaba.fastjson.JSONObject;
import com.haycm.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taolei on 2017/8/15.
 */
@RestController
@RequestMapping(value = "student")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

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

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public JSONObject editStudent(@RequestBody JSONObject student) {
        return student;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JSONObject addStudent(@RequestBody JSONObject student) {
        return student;
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONObject deleteStudent(@RequestBody JSONObject student) {
        student.put("status","fail");
        return student;
    }
}
