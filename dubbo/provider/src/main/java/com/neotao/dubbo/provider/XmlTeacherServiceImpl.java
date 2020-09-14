package com.neotao.dubbo.provider;

import com.neotao.dubbo.api.XmlTeacherService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "1.1")
public class XmlTeacherServiceImpl implements XmlTeacherService {

    @Override
    public String sayHello(String name) {
        return "Hello ! " + name;
    }
}
