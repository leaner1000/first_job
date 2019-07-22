package com.javan.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CancelItem {
    private Integer cancelitem_id;

    private Integer cloth_id;

    private String size;

    private String color;

    private Integer num;

    private Float single;

    private Float total;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date date;

    private String status;

    private String des;

    public Integer getCancelitem_id() {
        return cancelitem_id;
    }

    public void setCancelitem_id(Integer cancelitem_id) {
        this.cancelitem_id = cancelitem_id;
    }

    public Integer getCloth_id() {
        return cloth_id;
    }

    public void setCloth_id(Integer cloth_id) {
        this.cloth_id = cloth_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Float getSingle() {
        return single;
    }

    public void setSingle(Float single) {
        this.single = single;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }
}