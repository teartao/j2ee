package com.haycm.controller;

import com.alibaba.fastjson.JSONObject;
import com.haycm.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 基于Restful风格架构测试
 *
 * @author dekota
 * @version V1.0
 * @history 2014-2-15 下午3:00:12 dekota 新建
 * @since JDK1.5
 */
@RestController
public class DekotaAction {

    /**
     * 日志实例
     */
    private static final Logger logger = LoggerFactory.getLogger(DekotaAction.class);

    @RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
    public String hello() {
        return "你好！hello";
    }

    @RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
    public String say(@PathVariable(value = "msg") String msg) {
        return "{\"msg\":\"you say:'" + msg + "'\"}";
    }

    @RequestMapping(value = "/personGet/{id:\\d+}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") int id) {
        logger.info("获取人员信息id = " + id);
        Person person = new Person();
        person.setName("张三");
        person.setSex("男");
        person.setAge(30);
        person.setId(id);
        return person;
    }

    @RequestMapping(value = "/personPost", method = RequestMethod.POST)
    public JSONObject addPerson(@RequestBody JSONObject person) {
//        logger.info("注册人员信息成功id=" + person.getId());
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("msg", "注册人员信息成功");
        return person;
    }

    @RequestMapping(value = "/personDel/{id:\\d+}", method = RequestMethod.DELETE)
    public Object deletePerson(@PathVariable("id") int id) {
        logger.info("删除人员信息id=" + id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "删除人员信息成功");
        return jsonObject;
    }

    @RequestMapping(value = "/personPut", method = RequestMethod.PUT)
    public Object updatePerson(Person person) {
        logger.info("更新人员信息id=" + person.getId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg", "更新人员信息成功");
        return jsonObject;
    }

    @RequestMapping(value = "/personPatch", method = RequestMethod.PATCH)
    public List<Person> listPerson(@RequestParam(value = "name", required = false, defaultValue = "") String name) {

        logger.info("查询人员name like " + name);
        List<Person> lstPersons = new ArrayList<Person>();

        Person person = new Person();
        person.setName("张三");
        person.setSex("男");
        person.setAge(25);
        person.setId(101);
        lstPersons.add(person);

        Person person2 = new Person();
        person2.setName("李四");
        person2.setSex("女");
        person2.setAge(23);
        person2.setId(102);
        lstPersons.add(person2);

        Person person3 = new Person();
        person3.setName("王五");
        person3.setSex("男");
        person3.setAge(27);
        person3.setId(103);
        lstPersons.add(person3);

        return lstPersons;
    }
}