package com.example.myproject.Class;

import java.util.List;

public class Room {
    private int idPhong;
    private List<Chair> ghe;

    public  Room(){}

    public Room(int idPhong, List<Chair> chair) {
        this.idPhong = idPhong;
        this.ghe = chair;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public List<Chair> getChair() {
        return ghe;
    }

    public void setChair(List<Chair> chair) {
        this.ghe = chair;
    }
}

