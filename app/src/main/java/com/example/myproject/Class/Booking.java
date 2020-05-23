package com.example.myproject.Class;

public class Booking {
    private String tenPhim;
    private String ngayDat;
    private String tenGhe;

    public Booking(){}

    public Booking(String tenPhim, String ngayDat, String tenGhe) {
        this.tenPhim = tenPhim;
        this.ngayDat = ngayDat;
        this.tenGhe = tenGhe;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getTenGhe() {
        return tenGhe;
    }

    public void setTenGhe(String tenGhe) {
        this.tenGhe = tenGhe;
    }
}
