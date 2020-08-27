package com.neotao.dubbo.provider;

import com.neotao.dubbo.api.AnnotationStudentService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class DubboConfig {

    //<dubbo:application name="boot-user-service-provider"></dubbo:application>
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-provider");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("192.168.0.211:2181");
        return registryConfig;
    }

    @Bean
    public ServiceConfig<AnnotationStudentService> userServiceConfig(AnnotationStudentService studentService){
        ServiceConfig<AnnotationStudentService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(AnnotationStudentService.class);
        serviceConfig.setRef(studentService);
        serviceConfig.setVersion("1.0.0");
        return serviceConfig;
    }
}
