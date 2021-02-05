package com.sheva.parkinglotdemo.service;

import com.sheva.parkinglotdemo.domain.entity.Menu;
import com.sheva.parkinglotdemo.domain.entity.jpa.MenuResult;

import java.util.List;

/**
 * @Author sheva
 * @create 2021/2/4 18:38
 */
public interface MenuService {

    /**
     * 查找所有菜单
     * @return 菜单list
     */
    List<Menu> findAll();
}
