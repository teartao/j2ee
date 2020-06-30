package com.neotao.testing.controller;

import com.neotao.testing.services.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private SomeService someService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ResponseBody
    @GetMapping("/someService")
    public Res<Object> someService() {
        return Res.res(someService.getSomeData("xx"));
    }

}
