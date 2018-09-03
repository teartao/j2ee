package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.entity.MenuList;
import com.tao.entity.Result;
import com.tao.entity.ResultEnum;
import com.tao.service.FileService;
import com.tao.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@RestController
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Resource
    private MenuService menuService;
    @Resource
    private FileService fileService;

    @Value("${fileUploadPath}")
    private String filePath;

    @PostConstruct
    public void initFilePath() {
        File fileDir = new File(filePath);
        if (!fileDir.exists() && fileDir.mkdirs()) {
            logger.info("create filePath success : {}", filePath);
        }
    }


    @RequestMapping(value = "json", method = RequestMethod.GET)
    public JSONObject getJsonObj() throws IOException {
        JSONObject result = new JSONObject();
        result.put("aaa", "呵呵额");
        result.put("bbb", "222");
        result.put("ccc", "333");
        return result;
    }


    @RequestMapping(value = "menuList", method = RequestMethod.POST)
    public JSONObject addMenuList(@RequestBody MenuList menuList) {
        menuService.publishMenu(menuList);

        return new JSONObject();
    }

    @RequestMapping(value = "saveMenu", method = RequestMethod.POST)
    public Result<String> writeMenuToFile(@RequestParam("menuTxt") String menuString, HttpServletRequest request) throws UnsupportedEncodingException {
        menuString = new String(menuString.getBytes("iso8859-1"), "utf-8");
        try {
            fileService.writeStringToFile(filePath + "menu.data", menuString);
            logger.info("write string to file : [ {} ]\ncontent : \n{}", filePath + "menu.data", menuString);
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


    @RequestMapping(value = "menuTxt", method = RequestMethod.GET)
    public Result<String> getMenuFromFile() {
        try {
            String menuString = fileService.readLongTxtFromFile(filePath + "menu.data");
            logger.info("read string from file : [ {} ]\ncontent : \n{}", menuString, menuString);
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