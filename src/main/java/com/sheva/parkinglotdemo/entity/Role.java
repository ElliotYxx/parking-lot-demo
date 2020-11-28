package com.sheva.parkinglotdemo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author Sheva
 * @Date 2020/11/24
 */
@Entity
@Getter
@Setter
public class Role {
    @Id
    private Integer id;
    @Column
    private String roleName;
}
