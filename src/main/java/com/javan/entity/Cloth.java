package com.javan.entity;

public class Cloth {
    private Integer cloth_id;

    private String picture_path;

    private String des;

    private Integer s;

    private Integer m;

    private Integer l;

    private Integer xl;

    private Integer xl2;

    private Integer xl3;

    private Integer xl4;

    private Integer xl5;

    private Integer others;

    public Integer getCloth_id() {
        return cloth_id;
    }

    public void setCloth_id(Integer cloth_id) {
        this.cloth_id = cloth_id;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path == null ? null : picture_path.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public Integer getS() {
        return s;
    }

    public void setS(Integer s) {
        this.s = s;
    }

    public Integer getM() {
        return m;
    }

    public void setM(Integer m) {
        this.m = m;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getXl() {
        return xl;
    }

    public void setXl(Integer xl) {
        this.xl = xl;
    }

    public Integer getXl2() {
        return xl2;
    }

    public void setXl2(Integer xl2) {
        this.xl2 = xl2;
    }

    public Integer getXl3() {
        return xl3;
    }

    public void setXl3(Integer xl3) {
        this.xl3 = xl3;
    }

    public Integer getXl4() {
        return xl4;
    }

    public void setXl4(Integer xl4) {
        this.xl4 = xl4;
    }

    public Integer getXl5() {
        return xl5;
    }

    public void setXl5(Integer xl5) {
        this.xl5 = xl5;
    }

    public Integer getOthers() {
        return others;
    }

    public void setOthers(Integer others) {
        this.others = others;
    }
}