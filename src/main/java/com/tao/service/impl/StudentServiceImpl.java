package com.tao.service.impl;

import com.tao.dao.StudentDao;
import com.tao.entity.ExeResult;
import com.tao.entity.Student;
import com.tao.service.StudentService;
import net.hehe.web.ResponseStatus;
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
        ExeResult<List<Student>> result = new ExeResult<>();
        List<Student> students;
        try {
            students = studentDao.findList();
            result.setData(students);
            result.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setStatus(ResponseStatus.ERROR);
        }
        return result;
    }

    @Override
    public ExeResult<List<Student>> findById() {
        return null;
    }

    @Override
    public ExeResult<Integer> addStudent(Student student) {
        ExeResult<Integer> result = new ExeResult<>();
        try {
            result.setData(studentDao.addStudent(student));
            result.setMsg("添加成功");
            result.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setStatus(ResponseStatus.ERROR);
        }
        return result;
    }

    @Override
    public ExeResult<Integer> editStudent(Student student) {
        ExeResult<Integer> result = new ExeResult<>();
        try {
            result.setData(studentDao.editStudent(student));
            result.setMsg("保存成功");
            result.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setStatus(ResponseStatus.ERROR);
        }
        return result;
    }

    @Override
    public ExeResult<Integer> deleteStudent(Integer studentId) {
        ExeResult<Integer> result = new ExeResult<>();
        try {
            result.setData(studentDao.deleteStudent(studentId));
            result.setMsg("删除成功");
            result.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setStatus(ResponseStatus.ERROR);
        }
        return result;
    }
}
