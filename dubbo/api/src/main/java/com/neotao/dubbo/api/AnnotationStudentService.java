package com.neotao.dubbo.api;

import com.neotao.dubbo.entity.Student;

import java.util.List;

public interface AnnotationStudentService {

    List<Student> findStudent(Student student);
}
