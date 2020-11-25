package com.sheva.parkinglotdemo.repository;

import com.sheva.parkinglotdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

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
     * @param state 用户状态代码
     */
    @Modifying
    @Query("update User u set u.state = :state where u.id = :userId")
    void updateUserState(@Param("userId") Long userId, @Param("state") Integer state);

}
