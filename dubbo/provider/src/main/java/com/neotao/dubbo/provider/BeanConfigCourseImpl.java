package com.neotao.dubbo.provider;

import com.neotao.dubbo.api.BeanConfigCourseService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BeanConfigCourseImpl implements BeanConfigCourseService {
    @Override
    public List<String> queryCourses(String teacherName) {
        return Arrays.asList(teacherName, "Bruise Lee");
    }
}
