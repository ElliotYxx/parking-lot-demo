package com.sheva.parkinglotdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Sheva
 * @Date 2020/11/22
 */
@Slf4j
@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("index")
    public String userIndex(){
        return "user/index";
    }

}
