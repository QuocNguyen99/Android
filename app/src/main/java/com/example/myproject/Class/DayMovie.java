package com.example.myproject.Class;

public class DayMovie {
    private int idPhim;
    private int idRoom;

    public DayMovie(){}

    public DayMovie(int idPhim, int idRoom) {
        this.idPhim = idPhim;
        this.idRoom = idRoom;
    }

    public int getIdPhim() {
        return idPhim;
    }

    public void setIdPhim(int idPhim) {
        this.idPhim = idPhim;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
}
