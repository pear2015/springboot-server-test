package com.gs.crms.common.utils;

import com.gs.crms.common.model.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhufengjie on 2017/10/19.
 */
public class PageUtil {
    public static final  int PAGESIAE=10;

    private  PageUtil() {
    }
    /**
     * 生成排序规则
     *
     * @param list
     * @return
     */
    public Sort createSortRules(List<SortOrder> list) {
        List<Sort.Order> orders = new ArrayList<>();

        if (!list.isEmpty()) {
            for (SortOrder sortOrder : list) {
                if (sortOrder.getAscDsc() == 0) {
                    orders.add(new Sort.Order(Sort.Direction.ASC, sortOrder.getProperties()));
                } else {
                    orders.add(new Sort.Order(Sort.Direction.DESC, sortOrder.getProperties()));
                }
            }
            return new Sort(orders);
        }
        return null;
    }
    /**
     * 初始化分页
     */
    public static PageRequest initPage(int page,int pageSize){
        return  new PageRequest(page,pageSize==0?PageUtil.PAGESIAE:pageSize);
    }
}
