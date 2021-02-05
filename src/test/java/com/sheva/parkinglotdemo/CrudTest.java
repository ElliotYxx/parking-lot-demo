package com.sheva.parkinglotdemo;

import com.sheva.parkinglotdemo.domain.entity.Menu;
import com.sheva.parkinglotdemo.domain.entity.User;
import com.sheva.parkinglotdemo.domain.entity.jpa.MenuResult;
import com.sheva.parkinglotdemo.service.AdminService;
import com.sheva.parkinglotdemo.service.MenuService;
import com.sheva.parkinglotdemo.service.UserService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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

    @Autowired
    public MenuService menuService;

    @Test
    public void Test(){
    }
}
