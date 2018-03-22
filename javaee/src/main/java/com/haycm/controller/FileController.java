package com.haycm.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


/**
 * @Author neo·tao
 * @Date 2017/11/27
 * @Desc
 */
@RestController
public class FileController {
    @RequestMapping("/file/viewPic")
    public byte[] viewPic(HttpServletResponse response, HttpServletRequest request) throws IOException {
        File file = new File("D:/xxx.png");
        response.setContentType("image/png");
        return FileCopyUtils.copyToByteArray(file);
    }
}
