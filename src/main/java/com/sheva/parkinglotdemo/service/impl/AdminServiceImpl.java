package com.sheva.parkinglotdemo.service.impl;

import com.sheva.parkinglotdemo.domain.entity.User;
import com.sheva.parkinglotdemo.repository.UserRepository;
import com.sheva.parkinglotdemo.service.AdminService;
import com.sheva.parkinglotdemo.utils.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Author Sheva
 * @Date 2020/11/25
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Integer deleteUserByIds(String strIds) {
        Long[] ids = Convert.toLongArray(strIds);
        List<Long> userIds = Arrays.asList(ids);
        return userRepository.deleteUserByIds(userIds);
    }


    @Override
    public Integer changeStatus(User user) {
        return userRepository.updateUserStatus(user.getId(), user.getStatus());
    }
}
