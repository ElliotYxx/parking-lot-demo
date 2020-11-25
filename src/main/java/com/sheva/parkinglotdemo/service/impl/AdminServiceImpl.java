package com.sheva.parkinglotdemo.service.impl;

import com.sheva.parkinglotdemo.repository.UserRepository;
import com.sheva.parkinglotdemo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Sheva
 * @Date 2020/11/25
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserState(Long userId, Integer state) {
        userRepository.updateUserState(userId, state);
    }

}
