package com.tao.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tao.dao.StudentDao;
import com.tao.entity.ExeResult;
import com.tao.entity.Student;
import com.tao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by taolei on 2017/8/24.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public ExeResult<List<Student>> findList() {
        ExeResult<List<Student>> result = new ExeResult<List<Student>>();
        List<Student> students;
        try {
            students = studentDao.findList();
            result.setData(students);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @Override
    public ExeResult<List<Student>> findById() {
        return null;
    }

    @Override
    public ExeResult<Integer> addStudent(Student student) {
        ExeResult<Integer> result = new ExeResult<Integer>();
        result.setData(1);
        result.setMsg("删除成功");
        return null;
    }

    @Override
    public ExeResult<Integer> editStudent(Student student) {
        return null;
    }

    @Override
    public ExeResult<Integer> deleteStudent(Integer studentId) {
        ExeResult<Integer> result = new ExeResult<Integer>();
       Integer rs;
        try {
            rs = studentDao.deleteStudent(studentId);
            result.setData(rs);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
