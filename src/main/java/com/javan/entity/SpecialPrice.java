package com.javan.entity;

import java.math.BigDecimal;

public class SpecialPrice extends SpecialPriceKey {
    private BigDecimal default_price;

    private String unused1;

    private String unused2;

    public BigDecimal getDefault_price() {
        return default_price;
    }

    public void setDefault_price(BigDecimal default_price) {
        this.default_price = default_price;
    }

    public String getUnused1() {
        return unused1;
    }

    public void setUnused1(String unused1) {
        this.unused1 = unused1 == null ? null : unused1.trim();
    }

    public String getUnused2() {
        return unused2;
    }

    public void setUnused2(String unused2) {
        this.unused2 = unused2 == null ? null : unused2.trim();
    }
}