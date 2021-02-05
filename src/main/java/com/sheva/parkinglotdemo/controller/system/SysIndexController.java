package com.sheva.parkinglotdemo.controller.system;

import com.sheva.parkinglotdemo.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Sheva
 * @Date 2020/12/5
 */
@Slf4j
@Controller
public class SysIndexController {
    /**
     * 主界面
     * @param map
     * @param request
     * @return
     */
    @GetMapping("/index")
    public String index(ModelMap map, HttpServletRequest request){
        User currUser = (User)request.getSession().getAttribute("user");
        log.info("当前用户为：[" + currUser.toString() + "]");
        map.put("user", currUser);
        return "index";
    }

    @GetMapping("/system/main")
    public String main(){
        return "main";
    }
}
