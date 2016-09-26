package com.xedaojia.crowdsource.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class OrderStatusCallback {
    private Integer id;

    @NotEmpty
    private String orderId;
    @NotEmpty
    private String tradeOrderId;
    
    /*
     * 订单状态  1000  已派单, 1001  重新派单, 1002  开始配送, 1100  配送完成, 
	 * 2000  客户拒收, 2001  无法与客户联系, 2002  物品破损或丢失, 2100  配送失败
     */
    @Digits(fraction = 0, integer = 4)
    private Integer orderStatus;
    
    private String deliverymanName;

    private String deliverymanPhone;
    @Digits(fraction = 0, integer = 10)
    private Integer damage;
    @NotEmpty
    private String description;
    @DateTimeFormat(pattern="yyyyMMddHHmmss")
    private Date reqtime;
    @NotEmpty
    private String platform;
    @NotEmpty
    private String appid;
    @NotEmpty
    private String version;

    private Date createTime;

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

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDeliverymanName() {
        return deliverymanName;
    }

    public void setDeliverymanName(String deliverymanName) {
        this.deliverymanName = deliverymanName;
    }

    public String getDeliverymanPhone() {
        return deliverymanPhone;
    }

    public void setDeliverymanPhone(String deliverymanPhone) {
        this.deliverymanPhone = deliverymanPhone;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReqtime() {
        return reqtime;
    }

    public void setReqtime(Date reqtime) {
    	this.reqtime = reqtime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public void setTrade_order_id(String trade_order_id){
    	this.tradeOrderId = trade_order_id;
    }
    
    public void setXe_order_id(String xe_order_id){
    	this.orderId = xe_order_id;
    }
    
    public void setOrder_status(Integer order_status){
    	this.orderStatus = order_status;
    }
    
    public void setDeliveryman_name(String deliveryman_name){
    	this.deliverymanName = deliveryman_name;
    }
  
    public void setDeliveryman_phone(String deliveryman_phone){
    	this.deliverymanPhone = deliveryman_phone;
    }
    
}