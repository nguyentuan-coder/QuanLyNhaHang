package com.example.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlynhahang.DTO.PhanQuyenDTO;
import com.example.quanlynhahang.Database.DataBaseHelber;

import java.util.ArrayList;
import java.util.List;

public class QuyenDAO {
    SQLiteDatabase database;
    public QuyenDAO(Context context){
        DataBaseHelber dataBaseHelber = new DataBaseHelber(context);
       database= dataBaseHelber.open();
    }
    public void ThemQuyen(String tenquyen){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelber.TB_QUYENNV_TENQUYEN,tenquyen);
        database.insert(DataBaseHelber.TB_QUYENNV,null,contentValues);
    }
    public int LayQuyenNv(int manv){
        int maquyen =0;
        String truyvan = " SELECT * FROM "+DataBaseHelber.TB_TAIKHOAN+" WHERE "+DataBaseHelber.TB_TAIKHOAN_MATK + " = "+manv;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToNext();
        while (!cursor.isAfterLast()){
            PhanQuyenDTO quyenDTO = new PhanQuyenDTO();
            int cotmaquyen = cursor.getColumnIndex(DataBaseHelber.TB_TAIKHOAN_MAQ);
            quyenDTO.setMaQuyen(cursor.getInt(cotmaquyen));
            cursor.moveToNext();
        }
        return maquyen;
    }
    public List<PhanQuyenDTO> LayDanhSachQuyen(){
        List<PhanQuyenDTO> dsQuyen = new ArrayList<PhanQuyenDTO>();
        String truyvan ="SELECT * FROM "+DataBaseHelber.TB_QUYENNV;
        Cursor cursor = database.rawQuery(truyvan,null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()){
            PhanQuyenDTO quyenDTO = new PhanQuyenDTO();
            int cotmaquyen = cursor.getColumnIndex(DataBaseHelber.TB_QUYENNV_MAQ);
            quyenDTO.setMaQuyen(cursor.getInt(cotmaquyen));
            int tenquyen = cursor.getColumnIndex(DataBaseHelber.TB_QUYENNV_TENQUYEN);
            quyenDTO.setTenQuyen(cursor.getString(tenquyen));
            dsQuyen.add(quyenDTO);
            cursor.moveToNext();
        }
        return dsQuyen;
    }

}
