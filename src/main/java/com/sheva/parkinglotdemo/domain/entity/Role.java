package com.sheva.parkinglotdemo.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @Author Sheva
 * @Date 2020/11/24
 */
@Data
@Entity
public class Role {
    @Id
    private Integer id;
    @Column
    private String roleName;
}
