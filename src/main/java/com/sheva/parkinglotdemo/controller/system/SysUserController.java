package com.sheva.parkinglotdemo.controller.system;

import com.sheva.parkinglotdemo.controller.BaseController;
import com.sheva.parkinglotdemo.entity.AjaxResult;
import com.sheva.parkinglotdemo.entity.User;
import com.sheva.parkinglotdemo.service.UserService;
import com.sheva.parkinglotdemo.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息相关操作
 * @Author Sheva
 * @Date 2020/12/6
 */
@Slf4j
@Controller
@RequestMapping("/user/profile")
public class SysUserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/resetPwd")
    public String resetPwd(Model model, HttpServletRequest request){
        User currUser = (User) request.getSession().getAttribute("user");
        model.addAttribute("userId", currUser.getId());
        model.addAttribute("loginName", currUser.getUsername());
        return "user/resetPwd";
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(HttpServletRequest request){
        //输入的新密码
        String newPwd = request.getParameter("newPassword");
        User currUser = (User)request.getSession().getAttribute("user");
        log.info("修改密码的用户为：[" + currUser.toString() + "]");
        String username = currUser.getUsername();
        String pwd = currUser.getPassword();
        String encryptPwd = EncryptUtil.encryptPassword(username, newPwd);
        if ((encryptPwd).equals(pwd)){
            return error("新密码不能与旧密码相同...");
        }
        if (userService.resetUserPwd(currUser.getId(), encryptPwd) > 0){
            log.info("用户 [" + username + "] 密码修改成功...");
            return success();
        }
        return error("密码修改异常,请联系管理员...");
    }

    @GetMapping("/list")
    public String list(ModelMap map, HttpServletRequest request){
        User currUser = (User)request.getSession().getAttribute("user");
        log.info("当前用户为：[" + currUser.toString() + "]");
        map.put("user", currUser);
        return "user/list";
    }
}
