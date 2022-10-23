package com.example.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlynhahang.DTO.MonAnDTO;
import com.example.quanlynhahang.Database.DataBaseHelber;

import java.util.ArrayList;
import java.util.List;

public class MonAnDAO {
    SQLiteDatabase sqLiteOpenHelper;
    public MonAnDAO(Context context){
        DataBaseHelber dataBaseHelber = new DataBaseHelber(context);
        sqLiteOpenHelper = dataBaseHelber.open();
    }
    public boolean AddFood(MonAnDTO foodDTO){
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
    public List<MonAnDTO> LayDSMonTheoLoai(int maloai){
        List<MonAnDTO> foodDTOS = new ArrayList<MonAnDTO>();
        String truyvan = " SELECT * FROM "+DataBaseHelber.TB_MONAN+" WHERE "+DataBaseHelber.TB_MONAN_MALOAI + " = '"+maloai+"' ";
        Cursor cursor = sqLiteOpenHelper.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            MonAnDTO foodDTO = new MonAnDTO();
            int cotenTenmonan = cursor.getColumnIndex(DataBaseHelber.TB_MONAN_TENMONAN);
            foodDTO.setTenMonAn(cursor.getString(cotenTenmonan));
            int cotGiaMon = cursor.getColumnIndex(DataBaseHelber.TB_MONAN_GIATIEN);
            foodDTO.setGiaTien(cursor.getString(cotGiaMon));
            int cotMaMon = cursor.getColumnIndex(DataBaseHelber.TB_MONAN_MAMONAN);
            foodDTO.setMaMonAn(cursor.getInt(cotMaMon));
            int maLoai = cursor.getColumnIndex(DataBaseHelber.TB_MONAN_MALOAI);
            foodDTO.setMaLoai(cursor.getInt(maLoai));
            foodDTOS.add(foodDTO);
            cursor.moveToNext();
        }
        return foodDTOS;
    }

}
