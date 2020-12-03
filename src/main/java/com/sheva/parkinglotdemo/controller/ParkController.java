package com.sheva.parkinglotdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Sheva
 * @Date 2020/12/1
 */
@Slf4j
@Controller
@RequestMapping("car")
public class ParkController {

    @PostMapping("park")
    public String park(){
        return "";
    }
}
