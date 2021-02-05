package com.sheva.parkinglotdemo.repository;

import com.sheva.parkinglotdemo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户表的相关操作
 * @Author Sheva
 * @Date 2020/11/24
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return 用户实体类
     */
    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);

    /**
     * 通过用户名查询角色信息
     * @param username
     * @return
     */
    @Query("select r.roleName from Role r , User u where u.username = :username and u.roleId = r.id")
    String findRoles(@Param("username") String username);

    /**
     * 更新用户状态
     * @param userId 用户id
     * @param status 用户状态代码
     */
    @Modifying
    @Transactional
    @Query("update User u set u.status = :status where u.id = :userId")
    Integer updateUserStatus(@Param("userId") Long userId, @Param("status") Integer status);

    @Modifying
    @Transactional
    @Query("delete from User u where u.id in (?1)")
    Integer deleteUserByIds(List<Long> ids);
    /**
     * 修改用户的密码
     * @param userId 用户id
     * @param password 用户密码
     * @return
     */
    @Modifying
    @Transactional
    @Query("update User u set u.password = :password where u.id = :userId")
    Integer resetUserPwd(@Param("userId") Long userId, @Param("password") String password);
}
