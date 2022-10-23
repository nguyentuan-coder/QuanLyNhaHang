package com.example.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlynhahang.DTO.BanAnDTO;
import com.example.quanlynhahang.Database.DataBaseHelber;

import java.util.ArrayList;
import java.util.List;

public class BanAnDAO {
    SQLiteDatabase sqLiteDatabase;
    public BanAnDAO(Context context){
        DataBaseHelber dataBaseHelber = new DataBaseHelber(context);
        sqLiteDatabase = dataBaseHelber.open();

    }
    public boolean AddFoodTable(String tenBan){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelber.TB_BANAN_TENBAN,tenBan);
        contentValues.put(DataBaseHelber.TB_BANAN_TINHTRANG,"false");
        long kt = sqLiteDatabase.insert(DataBaseHelber.TB_BANAN,null,contentValues);
        if (kt != -1 ) {
            return true;
        } else {
            return false;
        }
    }
    public List<BanAnDTO> danhAllBanAn(){
        List<BanAnDTO> banAnDTOList = new ArrayList<BanAnDTO>();
        String truyvan = "SELECT * FROM "+DataBaseHelber.TB_BANAN;

        // kiểm soát vị tri của hàng trog kết quả trả về
        Cursor cursor = sqLiteDatabase.rawQuery(truyvan,null);
        cursor.moveToFirst() ;    // đến vị trí đầu tiên mà con trỏ truy vấn được cứ thế lần lượt tới vt thứ 2, thứ 3 .. cho tới hết dữ liệu
        while (!(cursor.isAfterLast())){   // khi nào con trỏ không còn là vị trí cuối cùng
            BanAnDTO foodTableDTO  = new BanAnDTO();
            int cotmabanan= cursor.getColumnIndex(DataBaseHelber.TB_BANAN_MABAN);
            foodTableDTO.setMaBan(cursor.getInt(cotmabanan));
            int cottenbanan = cursor.getColumnIndex(DataBaseHelber.TB_BANAN_TENBAN);
            foodTableDTO.setTenBan(cursor.getString(cottenbanan));
            banAnDTOList.add(foodTableDTO);
            cursor.moveToNext(); // nhảy xuống 1 dòng
        }
        return  banAnDTOList;
    }
    public boolean XoaBanAN(int maban){
       long kt = sqLiteDatabase.delete(DataBaseHelber.TB_BANAN,DataBaseHelber.TB_BANAN_MABAN + " = "+maban,null);
        if (kt != 0){
            return true;
        } else {
            return false;
        }
    }
    public boolean UpdateTenBan(int maban,String tenban){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelber.TB_BANAN_TENBAN,tenban);
        long kt = sqLiteDatabase.update(DataBaseHelber.TB_BANAN,contentValues,DataBaseHelber.TB_BANAN_MABAN + " = '" +maban+ "'",null);
        if (kt != 0){
            return true;
        } else {
            return false;
        }

    }


}
