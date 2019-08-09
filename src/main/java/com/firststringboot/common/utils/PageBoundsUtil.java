package com.firststringboot.common.utils;

import com.firststringboot.pojo.BaseBoundVo;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;

public class PageBoundsUtil {


    private static final PageBounds DEFAULT_PAGE_BOUND = new PageBounds();


    private static final String DESC = "desc";


    private static final String ASC = "asc";


    private static final List<Order> DESC_ORDER = Order.formString("createTime");


    private static final List<Order> ASC_ORDER = Order.formString("createTime");


    public static PageBounds of() {
        return DEFAULT_PAGE_BOUND;
    }


    public static PageBounds of(BaseBoundVo baseBoundVo) {
        if (baseBoundVo.getOrder() != null && baseBoundVo.getOrder() == DESC)
            return new PageBounds(baseBoundVo.getPageIndex(), baseBoundVo.getPageSize(), DESC_ORDER, true);
        return new PageBounds(baseBoundVo.getPageIndex(), baseBoundVo.getPageSize(), ASC_ORDER, true);
    }


}
