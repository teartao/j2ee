package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.entity.Result;
import com.tao.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "json", method = RequestMethod.GET)
    public JSONObject getJsonObj() throws IOException {
        JSONObject result = new JSONObject();
        result.put("aaa", "呵呵额");
        result.put("bbb", "222");
        result.put("ccc", "333");
        return result;
    }

    @RequestMapping(value = "getException", method = RequestMethod.GET)
    public JSONObject getException() throws IOException {
        throw new RuntimeException("has an exception");
    }

    @RequestMapping(value = "verifycode/create", method = RequestMethod.GET)
    public Result<JSONObject> createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result<JSONObject> result = new Result<>();
        String path = "xxx.img";
        File file = new File(request.getRealPath("/") + path);
        String verifyCode = setFileResponse(request, response, file);
        file.createNewFile();
        JSONObject data = new JSONObject();
        data.put("path", path);
        data.put("verifyCode", verifyCode);
        result.setResult(data, "0000", "生成成功");
        return result;
    }

    private String setFileResponse(HttpServletRequest request, HttpServletResponse response, File file) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtil.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        session.setAttribute("rand", verifyCode.toLowerCase());
        //生成图片
        int w = 200, h = 80;
        VerifyCodeUtil.outputImage(w, h, file, verifyCode);
        return verifyCode;
    }

    @RequestMapping(value = "user/create", method = RequestMethod.GET)
    public Result<Map> userRegister(HttpServletRequest request) {
        Result<Map> result = new Result<>();
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String verifyCode = request.getParameter("verifyCode");

        HttpSession session = request.getSession(true);
        if (verifyCode != null && !verifyCode.equalsIgnoreCase(String.valueOf(session.getAttribute("rand")))) {
            result.setCode("9999");
            result.setMessage("验证码错误");
            return result;
        }

        String sql = "INSERT INTO sysuser (username,password,state) values(?,?,?)";
        int exeRs = jdbcTemplate.update(sql, new Object[]{userName, password, 1});
        if (exeRs > 0) {
            result.setCode("0000");
            result.setMessage("注册成功");
            session.removeAttribute("rand");
        } else {
            result.setCode("9999");
            result.setMessage("注册失败");
        }
        return result;
    }

    @RequestMapping(value = "login1", method = RequestMethod.GET)
    public Result<Map> userLogin(HttpServletRequest request) {
        Result<Map> result = new Result<>();
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String sql = "SELECT * FROM sysuser t WHERE t.username=? AND t.password=?";
        List<Map<String, Object>> queryRs = jdbcTemplate.queryForList(sql, new Object[]{userName, password});
        if (queryRs != null && queryRs.size() > 0) {
            result.setCode("0000");
            result.setMessage("登陆成功");
        } else {
            result.setCode("9999");
            result.setMessage("登陆失败");
        }
        return result;
    }

    @Value("${fileupload.temppath}")
    private String fileUploadTempPath;

    @RequestMapping(value = "upload")
    public String fileUpload(String name, MultipartFile file,HttpServletResponse response,OutputStream os, HttpServletRequest request) throws IOException {
        //如果 file ！=null  表示文件上传成功

        //接下来是文件加载，为了方便我们直接将上传的文件发送给客户端下载
        if (!file.isEmpty()) {
            File jFile = new File(fileUploadTempPath + file.getOriginalFilename());
            file.transferTo(jFile);
            String fileName =  URLEncoder.encode(name+".xlsx", "UTF-8");

            //禁用缓存，防止IE下下载问题
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "No-cache");
            response.setDateHeader("Expires", 0);

            //设置文件以附件方式下载而不是在线浏览
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            //设置文件类型
            response.setContentType("application/x-xls;charset=UTF-8");
            FileCopyUtils.copy(file.getInputStream(), os);
            return "success";
        } else {
            return "failed";
        }
    }

    @ResponseBody
    @RequestMapping(value = "upload/ueditor")
    public Map<String, Object> upload(MultipartFile upfile, HttpServletRequest request, HttpServletResponse response) {
        // 文件读取路径
        Map<String, Object> params = new HashMap<String, Object>();
        try {
            String basePath = "D:/code/universe/ssm/target/ssm-1.0-SNAPSHOT";
            String visitUrl = "";
            String fileName = "";
            if (upfile != null) {
                //文件原名
                String ext = upfile != null ? upfile.getOriginalFilename() : "";
                //生成文件名
                fileName = String.valueOf(System.currentTimeMillis()).concat(ext);

                //写文件到磁盘或其它指定位置
                StringBuilder sb = new StringBuilder();
                sb.append(basePath).append("/").append(fileName);
                visitUrl = visitUrl.concat(fileName);
                File f = new File(sb.toString());
                if (!f.exists()) {
                    f.getParentFile().mkdirs();
                }
                OutputStream out = new FileOutputStream(f);
                FileCopyUtils.copy(upfile.getInputStream(), out);

                params.put("size", upfile.getSize());
                params.put("type", upfile.getContentType());
                params.put("original", fileName);
                params.put("url", visitUrl);
                params.put("state", "SUCCESS");
            }else{
                params.put("state", "文件不存在");
            }

        } catch (Exception e) {
            params.put("state", "ERROR");
        }
        return params;
    }
}