package com.sheva.parkinglotdemo;

import com.sheva.parkinglotdemo.entity.User;
import com.sheva.parkinglotdemo.service.AdminService;
import com.sheva.parkinglotdemo.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author Sheva
 * @Date 2020/11/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CrudTest {

    @Autowired
    public UserService userService;

    @Autowired
    public AdminService adminService;

    @Test
    public void userServiceTest(){
        userService.saveUser(new User("admin", "admin"));
        adminService.updateUserState(1L, 0);

    }
}
