package com.example.quanlynhahang.DTO;

public class BanAnDTO {
    int maBan;
    String tenBan;

    public boolean isDuocchon() {
        return duocchon;
    }

    public void setDuocchon(boolean duocchon) {
        this.duocchon = duocchon;
    }

    boolean duocchon;

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }
}
