package com.example.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlynhahang.DTO.LoaiMonAnDTO;
import com.example.quanlynhahang.Database.DataBaseHelber;

import java.util.ArrayList;
import java.util.List;

public class LoaiMonAnDAO {
    SQLiteDatabase sqLiteDatabase;
    public LoaiMonAnDAO(Context context){
        DataBaseHelber dataBaseHelber = new DataBaseHelber(context);
        sqLiteDatabase = dataBaseHelber.open();
    }
    public boolean ThemLoaiMonAn(String tenLoai){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelber.TB_LOAIMONAN_TENLOAI,tenLoai);
        long kt = sqLiteDatabase.insert(DataBaseHelber.TB_LOAIMONAN,null,contentValues);
        if (kt != 0){
            return true;
        } else {
            return false;
        }

    }
    public List<LoaiMonAnDTO> foodTypeDTOList(){
        List<LoaiMonAnDTO> typeDTOList = new ArrayList<>();
        String sLoaiMenu = " SELECT * FROM "+DataBaseHelber.TB_LOAIMONAN;
        Cursor cursor = sqLiteDatabase.rawQuery(sLoaiMenu,null);
        cursor.moveToNext();
        while (! cursor.isAfterLast()){
            LoaiMonAnDTO foodTypeDTO = new LoaiMonAnDTO();
            int cotmaloai= cursor.getColumnIndex(DataBaseHelber.TB_LOAIMONAN_MALOAI);
            foodTypeDTO.setMaLoai(cursor.getInt(cotmaloai));
            int cottenloai = cursor.getColumnIndex(DataBaseHelber.TB_LOAIMONAN_TENLOAI);
            foodTypeDTO.setTenLoai(cursor.getString(cottenloai));
            typeDTOList.add(foodTypeDTO);
            cursor.moveToNext();
        }
        return typeDTOList;
    }

}
