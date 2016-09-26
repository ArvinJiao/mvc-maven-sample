package com.xedaojia.crowdsource.domain;

import java.util.Date;

public class Ordertracks {
    private Integer id;

    private String zOrderId;

    private String orderId;

    private String parentId;

    private Integer orderStatus;

    private Integer orderType;

    private Integer operationType;

    private String operationId;

    private String operationName;

    private Integer roleType;

    private Integer orderOldStatus;

    private Integer orderOldType;

    private Date operationTime;

    private Date createTime;

    private Date updateTime;

    private String comments;

    private Integer thirdPartyType;

    private String operationContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getzOrderId() {
        return zOrderId;
    }

    public void setzOrderId(String zOrderId) {
        this.zOrderId = zOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getOrderOldStatus() {
        return orderOldStatus;
    }

    public void setOrderOldStatus(Integer orderOldStatus) {
        this.orderOldStatus = orderOldStatus;
    }

    public Integer getOrderOldType() {
        return orderOldType;
    }

    public void setOrderOldType(Integer orderOldType) {
        this.orderOldType = orderOldType;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getThirdPartyType() {
        return thirdPartyType;
    }

    public void setThirdPartyType(Integer thirdPartyType) {
        this.thirdPartyType = thirdPartyType;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }
}