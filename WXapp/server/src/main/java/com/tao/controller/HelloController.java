package com.tao.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tao.entity.*;
import com.tao.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tao.service.MenuService;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Resource
    private MenuService menuService;
    @Resource
    private FileService fileService;

    @Value("fileUploadPath")
    String fileUploadPath;

    @RequestMapping(value = "json", method = RequestMethod.GET)
    public JSONObject getJsonObj() throws IOException {
        JSONObject result = new JSONObject();
        result.put("aaa", "呵呵额");
        result.put("bbb", "222");
        result.put("ccc", "333");
        return result;
    }

    @RequestMapping(value = "menu", method = RequestMethod.POST)
    public JSONObject addMenu(@RequestBody MenuList menuList) {
        menuService.publishMenu(menuList);

        return new JSONObject();
    }

    @RequestMapping(value = "menu", method = RequestMethod.POST)
    public Result<String> addMenu(String menuString) {
        try {
            fileService.writeStringToFile(fileUploadPath, menuString);
            logger.info("write string to file : [ {} ]\ncontent : \n{}");
            Result<String> result = new Result<>();
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setMsg(ResultEnum.SUCCESS.getMsg());
            result.setData(menuString);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        Result<String> result = new Result<>();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(ResultEnum.ERROR.getMsg());
        return result;
    }
}