package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.dao.StudentDao;
import com.tao.entity.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StudentDao studentDao;
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Student> studentList() {
        return studentDao.findList();
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
    public JSONObject deleteStudent() {
        JSONObject student = new JSONObject();
        student.put("status","fail");
        return student;
    }
}
