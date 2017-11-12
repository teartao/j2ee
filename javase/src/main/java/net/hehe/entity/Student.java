package net.hehe.entity;

import net.hehe.annotation.Autowired;

/**
 * @Author neo·tao
 * @Date 2017/11/12
 * @Desc
 */
public class Student {
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void speak() {
        System.out.println("My name is " + name + ";I'm " + age + "years old!");
    }
}
