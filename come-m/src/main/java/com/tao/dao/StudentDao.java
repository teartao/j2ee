package com.tao.dao;

import com.tao.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by taolei on 2017/8/24.
 */
public interface StudentDao {
    List<Student> findList();
    List<Student> findById(@Param("studentId") Integer studentId);

    int addStudent(@Param("student")Student student);

    int editStudent(@Param("student")Student student);

    int deleteStudent(@Param("studentId") Integer studentId);
}
