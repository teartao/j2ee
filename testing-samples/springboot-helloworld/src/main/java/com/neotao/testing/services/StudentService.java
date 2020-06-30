package com.neotao.testing.services;

import com.neotao.testing.pojo.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> queryStudent(int age);

    void uploadPhoto(StudentDTO studentDTO);
}
