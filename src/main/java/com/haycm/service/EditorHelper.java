package com.haycm.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class EditorHelper {

    @Value("${apphome}")
    private String apphome;

    private static Logger log = LoggerFactory.getLogger(EditorHelper.class);

    public String upload(MultipartFile file) throws Exception {
        OutputStream out = null;
        InputStream in = file.getInputStream();
        try {
            String fileName = file.getOriginalFilename();
            String md5FileName = DigestUtils.md5Hex(fileName+ UUID.randomUUID().toString());
            out = new FileOutputStream(apphome+"\\"+md5FileName);
            StreamUtils.copy(in,out);
            return md5FileName;
        }finally {
            if(out!=null){
                out.close();
            }
            if(in!=null){
                in.close();
            }
        }
    }

    public InputStream download(String fileName){
        try {
            return new FileInputStream(apphome+"\\"+fileName);
        }catch (Exception e){
            throw new RuntimeException("can not find image");
        }
    }
}
