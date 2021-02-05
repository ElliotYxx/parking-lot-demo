package com.sheva.parkinglotdemo.controller.system;

import com.sheva.parkinglotdemo.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author sheva
 * @create 2021/2/4 18:28
 */
@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {

    private String prefix = "system/menu";

    @GetMapping()
    public String menu(){
        return prefix + "/menu";
    }
}
