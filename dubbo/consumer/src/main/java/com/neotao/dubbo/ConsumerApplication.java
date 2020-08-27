package com.neotao.dubbo;

import com.neotao.dubbo.api.AnnotationStudentService;
import com.neotao.dubbo.api.BeanConfigCourseService;
import com.neotao.dubbo.api.XmlTeacherService;
import com.neotao.dubbo.entity.Student;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
@EnableDubbo(scanBasePackages = "org.apache.dubbo.samples.simple.annotation.action")
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Reference(check = false)
    private AnnotationStudentService studentService;
    @Reference(check = false)
    private BeanConfigCourseService courseService;
    @Reference(check = false)
    private XmlTeacherService teacherService;

    @GetMapping("stu")
    public List<Student> getStudent() {
        return studentService.findStudent(Student.builder().name("陶呵呵").build());
    }

    @GetMapping("teach")
    public String getTeacher() {
        return teacherService.sayHello("chiebot");
    }

    @GetMapping("course")
    public List<String> getCourse() {
        return courseService.queryCourses("English");
    }


}
