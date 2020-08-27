package com.neotao.dubbo.provider;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//注解配置：自动扫描
// @EnableDubbo(scanBasePackages = "com.neotao.dubbo.provider")

//xml配置
@ImportResource("classpath:provider.xml")
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
