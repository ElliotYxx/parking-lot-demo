package com.sheva.parkinglotdemo.entity;

import lombok.Data;

/**
 * @Author Sheva
 * @Date 2020/11/24
 */
@Data
public class AjaxReturnMsg {

    private Integer flag;
    private String username;
    private String msg;

    public AjaxReturnMsg(){}
}
