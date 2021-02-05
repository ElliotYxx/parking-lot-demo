package com.sheva.parkinglotdemo.service;

import com.sheva.parkinglotdemo.domain.entity.User;

import java.util.List;

/**
 * @Author Sheva
 * @Date 2020/11/24
 */
public interface UserService {
    /**
     * 保存用户
     * @param user 用户
     * @return 用户
     */
    User saveUser(User user);

    /**
     * 通过用户id查找用户
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 查找所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 角色名
     **/
    String findRoles(String username);

    /**
     * 修改用户密码
     * @param id 用户id
     * @param password 用户密码
     * @return
     */
    Integer resetUserPwd(Long id, String password);
}
