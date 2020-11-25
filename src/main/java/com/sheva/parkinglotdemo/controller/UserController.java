package com.sheva.parkinglotdemo.controller;

import com.sheva.parkinglotdemo.entity.AjaxReturnMsg;
import com.sheva.parkinglotdemo.entity.User;
import com.sheva.parkinglotdemo.service.UserService;
import com.sheva.parkinglotdemo.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author Sheva
 * @Date 2020/11/22
 */
@Slf4j
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("index")
    public String userIndex(){
        return "user/index";
    }

    @GetMapping("userList")
    public String findUser(){
        User user = userService.findUserById(1L);
        log.info(user.toString());
        return "user/index";
    }

    @PostMapping("login")
    @ResponseBody
    public AjaxReturnMsg login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        AjaxReturnMsg msg = new AjaxReturnMsg();
        log.info("接收到的用户名：" + username + "  接收到的密码：" + password);
        User user = userService.findByUsername(username);
        if (null == user || !user.getPassword().equals(EncryptUtil.encryptPassword(username, password))){
            log.error("用户不存在或密码错误...");
            return msg;
        }

        msg.setFlag(1);
        msg.setUsername(username);
        log.info("用户:" + username + "登录成功...");
        return msg;
    }

    @PostMapping("register")
    @ResponseBody
    public AjaxReturnMsg register(HttpServletRequest req) throws Exception{
        AjaxReturnMsg msg = new AjaxReturnMsg();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("re_password");

        if(null == username || null == password || ("").equals(username) || ("").equals(password)){
            log.warn("未输入用户名或密码...");
            throw new AuthenticationException();
        }
        if (!rePassword.equals(password)){
            log.warn("前后密码输入不一致...");
            return msg;
        }

        //数据库中不存在重复用户名
        if (userService.findByUsername(username) == null){
            //通过md5算法对用户密码进行加密并存入数据库
            String encryptPwd = EncryptUtil.encryptPassword(username, password);
            userService.saveUser(new User(username, encryptPwd));
            log.info("用户" + username + "注册成功" + ",默认的权限为user");
            msg.setUsername(username);
            msg.setFlag(1);
        }else{
            log.warn("数据库中已存在该用户");
        }
        return msg;

    }
}
