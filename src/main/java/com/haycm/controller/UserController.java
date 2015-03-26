package com.haycm.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.haycm.entity.User;
import com.haycm.service.UserService;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource(name = "userService")
	private UserService userService;

}
