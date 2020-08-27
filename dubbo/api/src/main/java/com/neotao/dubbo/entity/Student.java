package com.neotao.dubbo.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class Student implements Serializable {
    private String name;
    private Integer age;
    private List<String> hobby;
}
