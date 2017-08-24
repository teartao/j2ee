package com.tao.service;

import com.tao.entity.ExeResult;
import com.tao.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by taolei on 2017/8/24.
 */
public interface StudentService {
    ExeResult<List<Student>> findList();
    ExeResult<List<Student>> findById();

    ExeResult<Integer> addStudent(Student student);

    ExeResult<Integer> editStudent(Student student);

    ExeResult<Integer> deleteStudent(Integer studentId);
}
