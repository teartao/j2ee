package com.tao.manager.impl;

import com.tao.manager.OrderFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HTDOrderManager implements OrderFactory {

    @Value("${fileUploadPath}")
    private String filePath;

    @Override
    public File getTodayOrderFile() throws IOException {
        //根据日期创建对应日期的点餐记录文件 order.YYYY-MM-dd.json
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
        String dateString = formatter.format(new Date());

        String orderFileFullName = filePath + "order." + dateString + ".json";
        File todayOrderFile = new File(orderFileFullName);
        //判断文件是否存在，存在则读取，否则创建
        if (!todayOrderFile.exists()) {
            boolean isSuccess = todayOrderFile.createNewFile();
        }
        return todayOrderFile;
    }

    @Override
    public void saveOrder(String content) throws IOException {
        File todayOrderFile=getTodayOrderFile();
        FileUtils.writeStringToFile(todayOrderFile, content, "utf-8");
    }
}
