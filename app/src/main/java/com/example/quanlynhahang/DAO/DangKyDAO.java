package com.example.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlynhahang.DTO.DangKyDTO;
import com.example.quanlynhahang.Database.DataBaseHelber;

import java.util.ArrayList;
import java.util.List;

public class DangKyDAO {
    // truy vấn cơ sở dữ liệu
    SQLiteDatabase sqLiteDatabase;
    public DangKyDAO(Context context){
        DataBaseHelber dataBaseHelber = new DataBaseHelber(context);
        sqLiteDatabase = dataBaseHelber.open();

    }
    public long DangKyTaiKhoan(DangKyDTO dangKyDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelber.TB_TAIKHOAN_TENDN,dangKyDTO.getTenDN());
        contentValues.put(DataBaseHelber.TB_TAIKHOAN_MATKHAU,dangKyDTO.getmKhau());
        contentValues.put(DataBaseHelber.TB_TAIKHOAN_GIOITINH,dangKyDTO.getGioiTinh());
        contentValues.put(DataBaseHelber.TB_TAIKHOAN_NGAYSINH,dangKyDTO.getNgaySinh());
        contentValues.put(DataBaseHelber.TB_TAIKHOAN_MAQL,dangKyDTO.getMaQuanLy());
        long kiemTra = sqLiteDatabase.insert(DataBaseHelber.TB_TAIKHOAN,null,contentValues);
        return kiemTra;
    }
    public boolean KiemTraDangNhap(String sTaiKhoanDK,String sMatKhauDK){
        String kt = "SELECT * FROM "+DataBaseHelber.TB_TAIKHOAN+" WHERE "+DataBaseHelber.TB_TAIKHOAN_TENDN +" = '"+sTaiKhoanDK+"' AND "+
                DataBaseHelber.TB_TAIKHOAN_MATKHAU +" = '"+sMatKhauDK+"'";
        Cursor cursor =sqLiteDatabase.rawQuery(kt,null);
        if (cursor.getCount() != 0){ // trả về hàng
            return true;
        }else {
            return false;
        }
    }
    public List<DangKyDTO> LayDSNhanVien() {
        List<DangKyDTO> dangKyDTOList = new ArrayList<>();
        String tv = "SELECT * FROM " + DataBaseHelber.TB_TAIKHOAN;
        Cursor cursor = sqLiteDatabase.rawQuery(tv, null);
        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            DangKyDTO dangKyDTO = new DangKyDTO();
            int ma = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_MATK);
            dangKyDTO.setMaTK(cursor.getInt(ma));
            int gt = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_GIOITINH);
            dangKyDTO.setGioiTinh(cursor.getString(gt));
            int ns = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_NGAYSINH);
            dangKyDTO.setNgaySinh(cursor.getString(ns));
            int tk = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_TENDN);
            dangKyDTO.setTenDN(cursor.getString(tk));
            int mk = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_MATKHAU);
            dangKyDTO.setmKhau(cursor.getString(mk));
            int mql = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_MAQL);
            dangKyDTO.setMaQuanLy(cursor.getString(mql));
            dangKyDTOList.add(dangKyDTO);
            cursor.moveToNext();
        }
        return dangKyDTOList;
    }
    public boolean XoaTaiKhoan(int ma){
        long kt = sqLiteDatabase.delete(DataBaseHelber.TB_TAIKHOAN,DataBaseHelber.TB_TAIKHOAN_MATK +" = "+ma,null);
        if (kt != 0){
            return true;
        } else {
            return false;
        }
    }
    public boolean SuaNV(DangKyDTO dangKyDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelber.TB_TAIKHOAN_TENDN,dangKyDTO.getTenDN());
        contentValues.put(DataBaseHelber.TB_TAIKHOAN_MATKHAU,dangKyDTO.getmKhau());
        contentValues.put(DataBaseHelber.TB_TAIKHOAN_GIOITINH,dangKyDTO.getGioiTinh());
        contentValues.put(DataBaseHelber.TB_TAIKHOAN_NGAYSINH,dangKyDTO.getNgaySinh());
        contentValues.put(DataBaseHelber.TB_TAIKHOAN_MAQL,dangKyDTO.getMaQuanLy());
        long kiemTra = sqLiteDatabase.update(DataBaseHelber.TB_TAIKHOAN, contentValues,DataBaseHelber.TB_TAIKHOAN_MATK+" = "+dangKyDTO.getMaTK(),null);
        if (kiemTra !=0){
            return true;
        } else {
            return false;
        }
    }
    public DangKyDTO LayDSNhanVienTheoMa( int ma) {
        DangKyDTO dangKyDTO = new DangKyDTO();
        String tv = "SELECT * FROM " + DataBaseHelber.TB_TAIKHOAN +" WHERE "+DataBaseHelber.TB_TAIKHOAN_MATK +" = "+ma;
        Cursor cursor = sqLiteDatabase.rawQuery(tv, null);
        cursor.moveToNext();
        while (!cursor.isAfterLast()) {
            int manv = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_MATK);
            dangKyDTO.setMaTK(cursor.getInt(ma));
            int gt = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_GIOITINH);
            dangKyDTO.setGioiTinh(cursor.getString(gt));
            int ns = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_NGAYSINH);
            dangKyDTO.setNgaySinh(cursor.getString(ns));
            int tk = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_TENDN);
            dangKyDTO.setTenDN(cursor.getString(tk));
            int mk = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_MATKHAU);
            dangKyDTO.setmKhau(cursor.getString(mk));
            int mql = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_MAQL);
            dangKyDTO.setMaQuanLy(cursor.getString(mql));
            cursor.moveToNext();
        }
        return dangKyDTO;
    }
}