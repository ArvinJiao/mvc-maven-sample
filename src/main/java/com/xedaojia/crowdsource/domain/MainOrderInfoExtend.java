package com.xedaojia.crowdsource.domain;

import java.util.Date;

public class MainOrderInfoExtend {
    private Integer id;

    private String orderId;

    private String tradeOrderId;

    private Date dispatchTime;

    private Integer zbMainId;

    private Integer thirdPartyType;

    private Integer sendCnt;

    private Date lastSendTime;

    private Integer orderStatusCallback;

    private String descCallback;

    private Integer damage;

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

    public String getTradeOrderId() {
        return tradeOrderId;
    }

    public void setTradeOrderId(String tradeOrderId) {
        this.tradeOrderId = tradeOrderId;
    }

    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public Integer getZbMainId() {
        return zbMainId;
    }

    public void setZbMainId(Integer zbMainId) {
        this.zbMainId = zbMainId;
    }

    public Integer getThirdPartyType() {
        return thirdPartyType;
    }

    public void setThirdPartyType(Integer thirdPartyType) {
        this.thirdPartyType = thirdPartyType;
    }

    public Integer getSendCnt() {
        return sendCnt;
    }

    public void setSendCnt(Integer sendCnt) {
        this.sendCnt = sendCnt;
    }

    public Date getLastSendTime() {
        return lastSendTime;
    }

    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    public Integer getOrderStatusCallback() {
        return orderStatusCallback;
    }

    public void setOrderStatusCallback(Integer orderStatusCallback) {
        this.orderStatusCallback = orderStatusCallback;
    }

    public String getDescCallback() {
        return descCallback;
    }

    public void setDescCallback(String descCallback) {
        this.descCallback = descCallback;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }
}