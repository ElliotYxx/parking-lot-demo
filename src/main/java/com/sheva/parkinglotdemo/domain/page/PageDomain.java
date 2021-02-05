package com.sheva.parkinglotdemo.domain.page;

import com.sheva.parkinglotdemo.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页数据
 * @Author sheva
 * @create 2021/2/3 13:47
 */
@Getter
@Setter
public class PageDomain {
    /** 当前记录起始索引 */
    private Integer pageNum;

    /** 每页显示记录数 */
    private Integer pageSize;

    /** 排序列 */
    private String orderByColumn;

    /** 排序的方向desc或者asc */
    private String isAsc = "asc";

    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

}
