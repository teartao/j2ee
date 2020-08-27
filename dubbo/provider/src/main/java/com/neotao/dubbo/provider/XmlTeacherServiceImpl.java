package com.neotao.dubbo.provider;

import com.neotao.dubbo.api.XmlTeacherService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class XmlTeacherServiceImpl implements XmlTeacherService {

    @Override
    public String sayHello(String name) {
        return "Hello ! " + name;
    }
}
