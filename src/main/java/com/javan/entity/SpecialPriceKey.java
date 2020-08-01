package com.javan.entity;

public class SpecialPriceKey {
    private Integer cloth_id;

    private String custom_id;

    public Integer getCloth_id() {
        return cloth_id;
    }

    public void setCloth_id(Integer cloth_id) {
        this.cloth_id = cloth_id;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id == null ? null : custom_id.trim();
    }
}