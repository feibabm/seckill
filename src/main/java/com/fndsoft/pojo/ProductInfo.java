package com.fndsoft.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by feibabm on 2017/9/9 0009.
 */
public class ProductInfo implements Serializable{
    private long productId;
    private long number;
    private Date startTime;
    private Date endTime;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public ProductInfo() {
    }

    public ProductInfo(long productId, long number, Date startTime, Date endTime) {
        this.productId = productId;
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
