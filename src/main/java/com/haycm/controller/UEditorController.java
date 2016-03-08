package com.haycm.controller;

import com.alibaba.fastjson.JSONObject;
import com.haycm.service.EditorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;


@Controller
public class UEditorController {

    @Autowired
    private EditorHelper editorHelper;

    @RequestMapping(value = "ueditor-config",method = RequestMethod.GET)
    public void readConfiguration(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String result = StreamUtils.copyToString(new ClassPathResource("uditor-config.json").getInputStream(), Charset.forName("utf-8"));
        response.getWriter().write(result);
    }
    //{"original":"demo.jpg","name":"demo.jpg","url":"\/server\/ueditor\/upload\/image\/demo.jpg","size":"99697","type":".jpg","state":"SUCCESS"}
    @RequestMapping(value = "ueditor-upload-image",method = RequestMethod.POST)
    public void uploadImage( @RequestParam("upfile")MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        try {
            String contextPath = request.getContextPath();
            if(!contextPath.startsWith("/")){
                contextPath = "/"+contextPath;
            }
            String fileName = editorHelper.upload(file);
            String url = "http://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/ckeditor-download/"+fileName;
            result.put("name",file.getOriginalFilename());
            result.put("original",file.getOriginalFilename());
            result.put("fileName",file.getOriginalFilename());
            result.put("size",file.getSize());
            result.put("type",file.getContentType());
            result.put("state","SUCCESS");
            result.put("url",url);
        }catch (Exception e){
            result.put("state","ERROR");
        }
        response.getWriter().print(result);
    }

}
