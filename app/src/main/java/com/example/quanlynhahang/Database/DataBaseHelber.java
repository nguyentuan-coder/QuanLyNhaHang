package com.example.quanlynhahang.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelber extends SQLiteOpenHelper {
    //khởi tạo các table
    public static String TB_TAIKHOAN ="TAIKHOAN";

    public static String TB_BANAN ="BANAN";
    public static String TB_MONAN="MONAN";
    public static String TB_LOAIMONAN="LOAIMONAN";
    public static String TB_CHITIETMON = "CHITIETMON";
    public static String TB_QUYENNV ="QUYEN";

    // bảng đăng ký
    public static String TB_TAIKHOAN_MATK="MATK";
    public static String TB_TAIKHOAN_TENDN="TENDN";
    public static String TB_TAIKHOAN_MAQ="MAQUYEN";
    public static String TB_TAIKHOAN_TENQUYEN="TENQUYEN";
    public static String TB_TAIKHOAN_MATKHAU="MKDN";
    public static String TB_TAIKHOAN_GIOITINH="GIOITINH";
    public static String TB_TAIKHOAN_NGAYSINH="NGAYSINH";
    public static String TB_TAIKHOAN_MAQL="MAQL";

    //bàn ăn
    public static String TB_BANAN_MABAN ="MABAN";
    public static String TB_BANAN_TENBAN ="TENBAN";
    public static String TB_BANAN_TINHTRANG ="TINHTRANG";

    // loại món ăn
    public static String TB_LOAIMONAN_MALOAI ="MALOAI";
    public static String TB_LOAIMONAN_TENLOAI ="TENLOAI";

    // món ăn
    public static String TB_MONAN_MAMONAN = "MAMONAN";
    public static String TB_MONAN_TENMONAN ="TENMONAN";
    public static String TB_MONAN_MALOAI="MALOAI";
    public static String TB_MONAN_GIATIEN="GIATIEN";

    // quyền
    public static String TB_QUYENNV_MAQ="MAQUYEN";
    public static String TB_QUYENNV_TENQUYEN="TENQUYEN";

    public DataBaseHelber(@Nullable Context context) {
        super(context,"QuanLyNhaHang",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tbDangKy= "CREATE TABLE "+TB_TAIKHOAN+"("+TB_TAIKHOAN_MATK+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TB_TAIKHOAN_TENDN
                +" TEXT, "+TB_TAIKHOAN_MATKHAU+" TEXT, "+TB_TAIKHOAN_GIOITINH+" TEXT, "+TB_TAIKHOAN_NGAYSINH+" TEXT, "
                +TB_TAIKHOAN_MAQL+" TEXT, " +TB_TAIKHOAN_MAQ+" INTEGER )";
        String tbBanAn = "CREATE TABLE " + TB_BANAN+"("+TB_BANAN_MABAN+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TB_BANAN_TENBAN+" TEXT, "
                +TB_BANAN_TINHTRANG+" TEXT)";
        String tbLoaiMonAN ="CREATE TABLE "+TB_LOAIMONAN+"("+TB_LOAIMONAN_MALOAI+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TB_LOAIMONAN_TENLOAI+" TEXT)";
        String tbMonAn ="CREATE TABLE "+TB_MONAN+"("+TB_MONAN_MAMONAN+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TB_MONAN_TENMONAN+" TEXT, "
                +TB_MONAN_MALOAI+ " INTEGER, "+TB_MONAN_GIATIEN+" TEXT)";
        String tbQuyen ="CREATE TABLE "+TB_QUYENNV+"("+TB_QUYENNV_MAQ+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TB_QUYENNV_TENQUYEN+" TEXT)";

        sqLiteDatabase.execSQL(tbDangKy);
        sqLiteDatabase.execSQL(tbBanAn);
        sqLiteDatabase.execSQL(tbLoaiMonAN);
        sqLiteDatabase.execSQL(tbMonAn);
        sqLiteDatabase.execSQL(tbQuyen);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }
}
