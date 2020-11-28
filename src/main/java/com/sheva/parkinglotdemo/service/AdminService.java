package com.sheva.parkinglotdemo.service;

/**
 * @Author Sheva
 * @Date 2020/11/25
 * 管理员相关操作
 */
public interface AdminService {

    /**
     * 删除用户
     * @param id 用户id
     */
    void deleteUser(Long id);

    /**
     * 更新用户状态
     * @param userId 用户id
     * @param state 用户状态
     */
    void updateUserState(Long userId, Integer state);

}
