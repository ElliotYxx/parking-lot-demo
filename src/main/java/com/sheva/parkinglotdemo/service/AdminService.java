package com.sheva.parkinglotdemo.service;

import com.sheva.parkinglotdemo.domain.entity.User;

/**
 * @Author Sheva
 * @Date 2020/11/25
 * 管理员相关操作
 */
public interface AdminService {

    /**
     * 删除用户
     * @param id 用户id
     * @return
     */
    void deleteUserById(Long id);

    /**
     * 批量删除用户
     * @param ids 用户id
     * @return
     */
    Integer deleteUserByIds(String ids);

    /**
     * 更新用户状态
     * @param user 用户实体类
     * @return
     */
    Integer changeStatus(User user);

}
