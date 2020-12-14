package com.sheva.parkinglotdemo.page;

import com.sheva.parkinglotdemo.constant.Constants;
import com.sheva.parkinglotdemo.utils.ServletUtils;

/**
 * 表格数据处理
 * @Author Sheva
 * @Date 2020/12/8
 */
public class TableSupport {
    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(Constants.PAGE_NUM));
        pageDomain.setPageSize(ServletUtils.getParameterToInt(Constants.PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(Constants.ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(Constants.IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }

}
