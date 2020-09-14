package com.xixiwan.platform.face.feign.order.service;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xixiwan.platform.module.common.util.DateUtils;
import com.xixiwan.platform.module.web.constant.WebConsts;
import com.xixiwan.platform.module.web.form.BaseForm;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

public interface CommonService {

    default <T> Page<T> getPage(BaseForm form) {
        Page<T> page = null;
        if (form != null) {
            String sortName = form.getSortName();
            String sortOrder = form.getSortOrder();
            long pageSize = form.getPageSize();
            long pageNumber = form.getPageNumber();
            page = new Page<>(pageNumber, pageSize);
            if (StringUtils.isNotBlank(sortName) && StringUtils.isNotBlank(sortOrder)) {
                if (WebConsts.SORTORDER_ASC.equalsIgnoreCase(sortOrder)) {
                    page.addOrder(OrderItem.asc(sortName));
                }
                if (WebConsts.SORTORDER_DESC.equalsIgnoreCase(sortOrder)) {
                    page.addOrder(OrderItem.desc(sortName));
                }
            } else {
                List<String> sortNames = form.getSortNames();
                String sortOrders = form.getSortOrders();
                if (WebConsts.SORTORDER_ASC.equalsIgnoreCase(sortOrders)) {
                    for (String s : sortNames) {
                        page.addOrder(OrderItem.asc(s));
                    }
                }
                if (WebConsts.SORTORDER_DESC.equalsIgnoreCase(sortOrders)) {
                    for (String s : sortNames) {
                        page.addOrder(OrderItem.desc(s));
                    }
                }
            }
        }
        return page;
    }

    default BaseForm getBaseForm(BaseForm form) {
        if (form != null) {
            String rangesDate = form.getRangesDate();
            if (StringUtils.isBlank(rangesDate)) {
                rangesDate = DateUtils.newThisMonth(new Date()) + " ~ " + DateUtils.lastThisMonth(new Date());
            }
            String[] rangesDateArray = rangesDate.split("~");
            if (rangesDateArray.length == 2) {
                form.setStartDateTime(
                        DateUtils.stringToLocalDateTime(rangesDateArray[0].trim() + " 00:00:00", DateUtils.TIME_PATTERN));
                form.setEndDateTime(
                        DateUtils.stringToLocalDateTime(rangesDateArray[1].trim() + " 23:59:59", DateUtils.TIME_PATTERN));
            }
        }
        return form;
    }

    Long generateKey();

    Long generateOrderKey();

}