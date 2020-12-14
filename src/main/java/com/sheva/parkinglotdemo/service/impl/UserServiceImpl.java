package com.sheva.parkinglotdemo.service.impl;

import com.sheva.parkinglotdemo.entity.User;
import com.sheva.parkinglotdemo.repository.UserRepository;
import com.sheva.parkinglotdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Sheva
 * @Date 2020/11/24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public String findRoles(String username) {
        return userRepository.findRoles(username);
    }

    @Override
    public Integer resetUserPwd(Long id, String password){ return userRepository.resetUserPwd(id, password); }
}
