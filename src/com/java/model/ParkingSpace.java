package com.java.model;

import java.util.Date;

public class ParkingSpace {
    public int cheWeiId;
    public String bianHao;
    public String leiBie;
    public String quYu;
    public String chePai;
    public Date wDate;

    public int getCheWeiId() {
        return cheWeiId;
    }

    public void setCheWeiId(int cheWeiId) {
        this.cheWeiId = cheWeiId;
    }

    public String getBianHao() {
        return bianHao;
    }

    public void setBianHao(String bianHao) {
        this.bianHao = bianHao;
    }

    public String getLeiBie() {
        return leiBie;
    }

    public void setLeiBie(String leiBie) {
        this.leiBie = leiBie;
    }

    public String getQuYu() {
        return quYu;
    }

    public void setQuYu(String quYu) {
        this.quYu = quYu;
    }

    public String getChePai() {
        return chePai;
    }

    public void setChePai(String chePai) {
        this.chePai = chePai;
    }

    public Date getwDate() {
        return wDate;
    }

    public void setwDate(Date wDate) {
        this.wDate = wDate;
    }

    public ParkingSpace(String bianHao, String leiBie, String quYu) {
        this.bianHao = bianHao;
        this.leiBie = leiBie;
        this.quYu = quYu;
    }
    public  ParkingSpace(){
        super();
    }
}
