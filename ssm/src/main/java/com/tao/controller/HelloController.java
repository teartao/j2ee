package com.tao.controller;

import com.alibaba.fastjson.JSONObject;
import com.tao.entity.Result;
import com.tao.utils.AuthImage;
import com.tao.utils.VerifyCodeUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HelloController {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "hello";
    }

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
//        data.put("verifyCode", verifyCode);
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
        String sql = "INSERT INTO sysuser (username,password,state) values(?,?,?)";
        int exeRs = jdbcTemplate.update(sql, new Object[]{userName, password,1});
        if (exeRs > 0) {
            result.setCode("0000");
            result.setMessage("注册成功");
        } else {
            result.setCode("9999");
            result.setMessage("注册失败");
        }
        return result;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
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
}