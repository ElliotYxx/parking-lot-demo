package com.sheva.parkinglotdemo.domain.page;

import com.sheva.parkinglotdemo.constant.Constants;
import com.sheva.parkinglotdemo.utils.ServletUtils;

/**
 *
 * 表格的数据处理
 * @Author sheva
 * @create 2021/2/3 13:55
 */
public class TableSupport {

    /**
     * 对分页对象进行封装
     */
    public static PageDomain getPageDomain(){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest(){
        return getPageDomain();
    }
}
