package com.tao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tao.annotation.Log;
import com.tao.common.Result;
import com.tao.common.ResultEnum;
import com.tao.entity.po.MenuList;
import com.tao.entity.po.Order;
import com.tao.business.MenuService;
import org.apache.commons.io.FileUtils;
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
import java.util.List;

@RestController
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Resource
    private MenuService menuService;

    @Value("${fileUploadPath}")
    private String filePath;

    @PostConstruct
    public void initFilePath() {
        File fileDir = new File(filePath);
        if (!fileDir.exists() && fileDir.mkdirs()) {
            logger.info("create filePath success : {}", filePath);
        }
    }

    /**
     * 保存菜单文本
     *
     * @param menuString
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @Log(description = "保存菜单")
    @RequestMapping(value = "saveMenu", method = RequestMethod.POST)
    public Result<JSONArray> writeMenuToFile(@RequestParam("menuTxt") String menuString, HttpServletRequest request) throws UnsupportedEncodingException {
        menuString = new String(menuString.getBytes("iso8859-1"), "utf-8");
        try {
            String absoluteFilePath = filePath + "menu.data";
            // todo parse menuString to menu list

            FileUtils.writeStringToFile(new File(absoluteFilePath), menuString, "utf-8");
            logger.info("write to file : [ {} ]\n[File Content]  : {}", absoluteFilePath, menuString);
            Result<JSONArray> result = new Result<>();
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setMsg(ResultEnum.SUCCESS.getMsg());
            result.setData(JSON.parseArray(menuString));
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        Result<JSONArray> result = new Result<>();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(ResultEnum.ERROR.getMsg());
        return result;
    }


    /**
     * 获取菜单文本
     *
     * @return
     */
    @Log(description = "获取菜单文本")
    @RequestMapping(value = "menuTxt", method = RequestMethod.GET)
    public Result<JSONArray> getMenuFromFile() {
        try {
            String absoluteFilePath = filePath + "menu.data";
            String menuString = FileUtils.readFileToString(new File(absoluteFilePath), "utf-8");
            logger.info("read from file : [ {} ]\n [File Content] : {}", absoluteFilePath, menuString);
            Result<JSONArray> result = new Result<>();
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setMsg(ResultEnum.SUCCESS.getMsg());
            result.setData(JSON.parseArray(menuString));
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        Result<JSONArray> result = new Result<>();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(ResultEnum.ERROR.getMsg());
        return result;
    }

    /**
     * 保存用户点餐选项
     */
    @Log(description = "保存用户点餐选项")
    @RequestMapping(value = "saveOrder", method = RequestMethod.POST)
    public Result<List<JSONObject>> saveOrder(@RequestBody List<JSONObject> selectionList) {
        try {
            String absoluteFilePath = filePath + "menu.data";
            FileUtils.writeStringToFile(new File(absoluteFilePath), JSON.toJSONString(selectionList), "utf-8");
            logger.info("write to file : [ {} ]\n[File Content]  : {}", absoluteFilePath, JSON.toJSONString(selectionList));
            Result<List<JSONObject>> result = new Result<>();
            result.setCode(ResultEnum.SUCCESS.getCode());
            result.setMsg(ResultEnum.SUCCESS.getMsg());
            result.setData(selectionList);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        Result<List<JSONObject>> result = new Result<>();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(ResultEnum.ERROR.getMsg());
        return result;
    }

    /**
     * 读取点餐选项
     */
    @Log(description = "读取点餐选项")
    @RequestMapping(value = "getOrders", method = RequestMethod.GET)
    public Result<List<Order>> getOrders() {
        return null;
    }
}