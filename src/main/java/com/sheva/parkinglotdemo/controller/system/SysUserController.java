package com.sheva.parkinglotdemo.controller.system;

import com.sheva.parkinglotdemo.controller.BaseController;
import com.sheva.parkinglotdemo.domain.AjaxResult;
import com.sheva.parkinglotdemo.domain.entity.User;
import com.sheva.parkinglotdemo.domain.page.TableDataInfo;
import com.sheva.parkinglotdemo.service.AdminService;
import com.sheva.parkinglotdemo.service.UserService;
import com.sheva.parkinglotdemo.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author sheva
 * @create 2021/2/4 18:02
 */
@Slf4j
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    private String prefix = "/system/user";

    /** 用户列表 **/
    @GetMapping()
    public String user(ModelMap map, HttpServletRequest request){
        User currUser = (User)request.getSession().getAttribute("user");
        map.put("user", currUser);
        return prefix + "/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(){
        startPage();
        List<User> list = userService.findAll();
        return getDataTable(list);
    }

    /** 用户列表 **/
    @GetMapping("/add")
    public String add(){
        return prefix + "/add";
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(String username, String password){
        User matching = userService.findByUsername(username);
        if (null != matching){
            log.error("该用户已存在...");
            return error("该用户名已存在...");
        }else{
            log.info("数据库中不存在重复用户，可以注册...");
            User user = new User(username, EncryptUtil.encryptPassword(username, password));
            userService.saveUser(user);
            log.info("用户：[" + username + "] 注册成功，默认权限为普通用户...");
            return success();
        }
    }
    /** 删除用户 **/
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids){
        return toAjax(adminService.deleteUserByIds(ids));
    }

    /**
     * 修改用户密码
     */
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap map){
        User editUser = userService.findUserById(userId);
        map.put("user", editUser);
        return prefix + "/reset";
    }

    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(HttpServletRequest request){
        String username = request.getParameter("username");
        String newPwd = request.getParameter("newPassword");
        User editUser = userService.findByUsername(username);
        log.info("修改密码的用户为：[" + username + "]");
        String encryptPwd = EncryptUtil.encryptPassword(username, newPwd);
        if (userService.resetUserPwd(editUser.getId(), encryptPwd) > 0){
            log.info("用户 [" + username + "] 密码修改成功...");
            return success();
        }
        return error("重置密码异常,请联系管理员...");
    }
    /**
     * 修改用户状态
     */
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(User user){
        return toAjax(adminService.changeStatus(user));
    }
}
