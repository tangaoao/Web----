package com.java.model;

import java.util.Date;

public class Bill {
    public int id;
    public String  billHao;
    public String billWDate;
    public String billLDate;
    public String billJine;
    public String billShijian;
    public int billPrice;
    public int billUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillHao() {
        return billHao;
    }

    public void setBillHao(String billHao) {
        this.billHao = billHao;
    }

    public String getBillWDate() {
        return billWDate;
    }

    public void setBillWDate(String billWDate) {
        this.billWDate = billWDate;
    }

    public String getBillLDate() {
        return billLDate;
    }

    public void setBillLDate(String billLDate) {
        this.billLDate = billLDate;
    }

    public String getBillJine() {
        return billJine;
    }

    public void setBillJine(String billJine) {
        this.billJine = billJine;
    }

    public String getBillShijian() {
        return billShijian;
    }

    public void setBillShijian(String billShijian) {
        this.billShijian = billShijian;
    }

    public int getBillPrice() {
        return billPrice;
    }

    public void setBillPrice(int billPrice) {
        this.billPrice = billPrice;
    }

    public int getBillUserId() {
        return billUserId;
    }

    public void setBillUserId(int billUserId) {
        this.billUserId = billUserId;
    }
}
