package com.sheva.parkinglotdemo.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author Sheva
 * @Date 2020/11/24
 */
@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;
    /** 用户状态 0正常  1停用 **/
    @Column
    private Integer status;
    @Column
    private Long roleId;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

}
