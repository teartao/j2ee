package com.tao.dao;

import com.tao.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by taolei on 2017/8/24.
 */
public interface StudentDao {
    List<Student> findList();
    List<Student> findById();

    int addStudent(Student student);

    int editStudent(Student student);

    int deleteStudent(@Param("studentId") Integer studentId);
}
