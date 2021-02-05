package com.sheva.parkinglotdemo.controller.system;

import com.sheva.parkinglotdemo.controller.BaseController;
import com.sheva.parkinglotdemo.domain.AjaxResult;
import com.sheva.parkinglotdemo.domain.entity.User;
import com.sheva.parkinglotdemo.service.UserService;
import com.sheva.parkinglotdemo.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证
 * @Author Sheva
 * @Date 2020/12/3
 */
@Slf4j
@Controller
public class SysLoginController extends BaseController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){

        if (ServletUtils.isAjaxRequest(request)){
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(HttpServletRequest request)
    {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        log.info("接收到的用户名：[" + username + "]" + " 密码：[" + password + "]");
        log.info("生成的token：[" + token.toString() + "]");
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(token);
            User user = userService.findByUsername(username);
            request.getSession().setAttribute("user", user);
            return success();
        }catch (AuthenticationException e){
            log.error(e.getMessage());
            String msg = "用户名或密码错误";
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}
