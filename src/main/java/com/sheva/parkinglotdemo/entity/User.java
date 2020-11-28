package com.sheva.parkinglotdemo.entity;

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
    private Timestamp createTime;
    @Column
    private Integer state;
    @Column
    private Long roleId;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

}
