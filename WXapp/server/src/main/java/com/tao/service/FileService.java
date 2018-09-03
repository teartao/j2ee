package com.tao.service;

import com.tao.controller.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author neotao
 * @Date 2018/9/3
 * @Version V0.0.1
 * @Desc
 */
@Service
public class FileService {
    private static Logger logger = LoggerFactory.getLogger(FileService.class);

    /**
     * write txt to file
     * @param fileName 文件名
     * @param content 字符内容
     */
    public void writeStringToFile(String fileName, String content) {

        try {
            File f = new File(fileName);
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            System.out.println("写文件内容操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 使用InputStreamReader类读文本文件
     */
    public String readLongTxtFromFile(String fileName) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        try {
            File f = new File(fileName);
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line);
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        return fileContent.toString();
    }
}
