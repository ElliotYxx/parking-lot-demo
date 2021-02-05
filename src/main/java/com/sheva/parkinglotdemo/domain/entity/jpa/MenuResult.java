package com.sheva.parkinglotdemo.domain.entity.jpa;

import com.sheva.parkinglotdemo.domain.entity.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义菜单结果返回类
 * @Author sheva
 * @create 2021/2/4 18:54
 */
@Data
public class MenuResult {

    private static final long serialVersionUID = 1L;

    private long menuId;

    private String menuName;

    private String parentName;

    private Long parentId;

    private String orderNum;

    private String url;

    private String menuType;

    private List<Menu> childMenu = new ArrayList<>();

}
