package com.neotao.dubbo.provider;

import com.neotao.dubbo.api.AnnotationStudentService;
import com.neotao.dubbo.entity.Student;
import org.apache.dubbo.config.annotation.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AnnotationStudentServiceImpl implements AnnotationStudentService {
    @Override
    public List<Student> findStudent(Student student) {
        List<Student> students = Arrays.asList(
                Student.builder().age(20).name("Jack").hobby(Arrays.asList("Piano", "Guitar")).build(),
                Student.builder().age(22).name("Tom").hobby(Arrays.asList("Math", "Poem")).build(),
                student
        );
        return students;
    }
}
