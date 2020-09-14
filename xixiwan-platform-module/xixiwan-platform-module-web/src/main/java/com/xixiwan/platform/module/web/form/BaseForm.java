package com.xixiwan.platform.module.web.form;

import java.time.LocalDateTime;
import java.util.List;

public class BaseForm {

    private String sortName;

    private String sortOrder;

    private long pageSize = 10;

    private long pageNumber = 1;

    private List<String> sortNames;

    private String sortOrders;

    private String rangesType;

    private String rangesDate;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<String> getSortNames() {
        return sortNames;
    }

    public void setSortNames(List<String> sortNames) {
        this.sortNames = sortNames;
    }

    public String getSortOrders() {
        return sortOrders;
    }

    public void setSortOrders(String sortOrders) {
        this.sortOrders = sortOrders;
    }

    public String getRangesType() {
        return rangesType;
    }

    public void setRangesType(String rangesType) {
        this.rangesType = rangesType;
    }

    public String getRangesDate() {
        return rangesDate;
    }

    public void setRangesDate(String rangesDate) {
        this.rangesDate = rangesDate;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
