package com.example.myproject.Class;

import android.widget.Button;

import com.example.myproject.R;

public class Chair {
    private String tenGhe;
    private String trangThai;
    public Boolean isSelected;

    public  Chair(){}

    public Chair(String tenGhe, String trangThai) {
        this.tenGhe = tenGhe;
        this.trangThai = trangThai;
    }

    public String getTenGhe() {
        return tenGhe;
    }

    public void setTenGhe(String tenGhe) {
        this.tenGhe = tenGhe;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setBackGround(Button btn){
        if(isSelected){
            btn.setBackgroundResource(R.drawable.custom_button_selected);
        }else{
            btn.setBackgroundResource(R.drawable.custom_buttonchair_dadat);
        }
    }
}
