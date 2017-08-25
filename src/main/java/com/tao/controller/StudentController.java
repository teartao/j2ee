package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.entity.ExeResult;
import com.tao.entity.Student;
import com.tao.service.StudentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by taolei on 2017/8/15.
 */
@Controller
@RequestMapping(value = "student")
public class StudentController {
    private static final Logger logger = Logger.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ExeResult<List<Student>> studentList() {
        return studentService.findList();
    }

    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ExeResult<Integer> editStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ExeResult<Integer> addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }


    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ExeResult<Integer> deleteStudent(@RequestBody Student student, HttpServletRequest request) {
        return studentService.deleteStudent(student.getId());
    }
}
