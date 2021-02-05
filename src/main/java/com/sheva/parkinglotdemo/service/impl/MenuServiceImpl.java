package com.sheva.parkinglotdemo.service.impl;

import com.sheva.parkinglotdemo.domain.entity.Menu;
import com.sheva.parkinglotdemo.domain.entity.jpa.MenuResult;
import com.sheva.parkinglotdemo.repository.MenuRepository;
import com.sheva.parkinglotdemo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author sheva
 * @create 2021/2/4 18:38
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
}
