package com.neotao.testing.dao;

import com.neotao.testing.pojo.StudentDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDao {

    public List<StudentDTO> queryList(int age) {
        return new ArrayList<>();
    }

    public void update(StudentDTO studentDTO) {
        System.out.println("将DB的studentDTO进行update");
    }
}
