package com.xedaojia.crowdsource.domain;

import java.util.Date;

public class OrderUpdateTask {
    private Integer id;

    private String orderId;

    private Integer tType;

    private String orderSendId;

    private String orderSendName;

    private String orderSendPhone;

    private Date getTime;

    private Date zCanelTime;

    private Date receiveTime;

    private Integer getStatus;

    private Integer sendStatus;

    private Integer cancelStatus;

    private Integer orderStatus;

    private String zCancelReason;

    private Integer returnStatus;

    private Integer informCnt;

    private Date createTime;

    private Date updateTime;

    private String finishCode;

    private String finishDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer gettType() {
        return tType;
    }

    public void settType(Integer tType) {
        this.tType = tType;
    }

    public String getOrderSendId() {
        return orderSendId;
    }

    public void setOrderSendId(String orderSendId) {
        this.orderSendId = orderSendId;
    }

    public String getOrderSendName() {
        return orderSendName;
    }

    public void setOrderSendName(String orderSendName) {
        this.orderSendName = orderSendName;
    }

    public String getOrderSendPhone() {
        return orderSendPhone;
    }

    public void setOrderSendPhone(String orderSendPhone) {
        this.orderSendPhone = orderSendPhone;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Date getzCanelTime() {
        return zCanelTime;
    }

    public void setzCanelTime(Date zCanelTime) {
        this.zCanelTime = zCanelTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getGetStatus() {
        return getStatus;
    }

    public void setGetStatus(Integer getStatus) {
        this.getStatus = getStatus;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(Integer cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getzCancelReason() {
        return zCancelReason;
    }

    public void setzCancelReason(String zCancelReason) {
        this.zCancelReason = zCancelReason;
    }

    public Integer getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Integer returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Integer getInformCnt() {
        return informCnt;
    }

    public void setInformCnt(Integer informCnt) {
        this.informCnt = informCnt;
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

    public String getFinishCode() {
        return finishCode;
    }

    public void setFinishCode(String finishCode) {
        this.finishCode = finishCode;
    }

    public String getFinishDesc() {
        return finishDesc;
    }

    public void setFinishDesc(String finishDesc) {
        this.finishDesc = finishDesc;
    }
}