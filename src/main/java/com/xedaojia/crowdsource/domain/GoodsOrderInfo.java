package com.xedaojia.crowdsource.domain;

import java.math.BigDecimal;
import java.util.Date;

public class GoodsOrderInfo {
    private String goodsOrderId;

    private String orderId;

    private String userId;

    private String goodsType;

    private String goodsId;

    private String goodsName;

    private BigDecimal goodsPrice;

    private BigDecimal goodsMarketPrice;

    private BigDecimal goodsAllmarkPrice;

    private Integer goodsCnt;

    private String goodsSpecInfo;

    private String goodsPic;

    private String goodsDesc;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer delWhere;

    private String gType;

    public String getGoodsOrderId() {
        return goodsOrderId;
    }

    public void setGoodsOrderId(String goodsOrderId) {
        this.goodsOrderId = goodsOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsMarketPrice() {
        return goodsMarketPrice;
    }

    public void setGoodsMarketPrice(BigDecimal goodsMarketPrice) {
        this.goodsMarketPrice = goodsMarketPrice;
    }

    public BigDecimal getGoodsAllmarkPrice() {
        return goodsAllmarkPrice;
    }

    public void setGoodsAllmarkPrice(BigDecimal goodsAllmarkPrice) {
        this.goodsAllmarkPrice = goodsAllmarkPrice;
    }

    public Integer getGoodsCnt() {
        return goodsCnt;
    }

    public void setGoodsCnt(Integer goodsCnt) {
        this.goodsCnt = goodsCnt;
    }

    public String getGoodsSpecInfo() {
        return goodsSpecInfo;
    }

    public void setGoodsSpecInfo(String goodsSpecInfo) {
        this.goodsSpecInfo = goodsSpecInfo;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getDelWhere() {
        return delWhere;
    }

    public void setDelWhere(Integer delWhere) {
        this.delWhere = delWhere;
    }

    public String getgType() {
        return gType;
    }

    public void setgType(String gType) {
        this.gType = gType;
    }
}