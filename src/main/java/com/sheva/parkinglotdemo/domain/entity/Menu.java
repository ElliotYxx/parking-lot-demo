package com.sheva.parkinglotdemo.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author sheva
 * @create 2021/2/4 18:17
 */
@Data
@Entity
public class Menu {
    /** 菜单id **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    /** 菜单名称 **/
    private String menuName;

    /** 父菜单id **/
    private Long parentId;

    /** 显示顺序 **/
    private String orderNum;

    /** 菜单url **/
    private String url;

    /** 菜单类型 M目录 C菜单 F按钮 **/
    private String menuType;

    /** 创建时间 **/
    private Timestamp createTime;

    /** 备注信息 **/
    private String remark;

}
