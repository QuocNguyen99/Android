package com.example.myproject.Class;

public class Flim {
    private int idPhim;
    private String tenPhim;
    private String hinhAnh;
    private String moTa;
    private String theLoai;
    private String thoiGian;
    private int idPhong;


    public Flim(){}

    public Flim(int idPhim, String tenPhim, String hinhAnh, String moTa, String theLoai, String thoiGian, int idPhong) {
        this.idPhim = idPhim;
        this.tenPhim = tenPhim;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.theLoai = theLoai;
        this.thoiGian = thoiGian;
        this.idPhong = idPhong;
    }

    public int getIdPhim() {
        return idPhim;
    }

    public void setIdPhim(int idPhim) {
        this.idPhim = idPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }
}
