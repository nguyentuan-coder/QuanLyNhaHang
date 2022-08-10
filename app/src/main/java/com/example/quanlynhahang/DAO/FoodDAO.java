package com.example.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlynhahang.DTO.FoodDTO;
import com.example.quanlynhahang.Database.DataBaseHelber;

public class FoodDAO {
    SQLiteDatabase sqLiteOpenHelper;
    public FoodDAO(Context context){
        DataBaseHelber dataBaseHelber = new DataBaseHelber(context);
        sqLiteOpenHelper = dataBaseHelber.open();
    }
    public boolean AddFood(FoodDTO foodDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelber.TB_MONAN_TENMONAN,foodDTO.getTenMonAn());
        contentValues.put(DataBaseHelber.TB_MONAN_GIATIEN,foodDTO.getGiaTien());
        contentValues.put(DataBaseHelber.TB_MONAN_MALOAI,foodDTO.getMaLoai());
        long kt = sqLiteOpenHelper.insert(DataBaseHelber.TB_MONAN,null,contentValues);
        if (kt != -1){
            return true;
        } else {
            return false;
        }
    }

}
