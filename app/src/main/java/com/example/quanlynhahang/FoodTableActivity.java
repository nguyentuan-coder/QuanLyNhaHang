package com.example.quanlynhahang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlynhahang.DAO.FoodTableDAO;
//dialog thêm bàn ăn

public class FoodTableActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edNhapTenBanThem;
    Button btnXacNhanThem,btnHuyThem;
    FoodTableDAO foodTableDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfoodtable);
        edNhapTenBanThem = findViewById(R.id.edNhapBanAnThem);
        btnXacNhanThem = findViewById(R.id.btnXNThem);
        btnHuyThem = findViewById(R.id.btnXNHuy);
        foodTableDAO = new FoodTableDAO(this);
        btnXacNhanThem.setOnClickListener(this);
        btnHuyThem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnXNThem){
            String sTenBan = edNhapTenBanThem.getText().toString();
            if (! sTenBan.isEmpty()){
                boolean kt = foodTableDAO.AddFoodTable(sTenBan);
                if (kt){
                    Intent itThem = new Intent();
                    itThem.putExtra("tenbanthem",sTenBan);
                    setResult(1,itThem);
                    finish();
                } else {
                    finish();
                }
            }
        }if (id == R.id.btnXNHuy){
            finish();
        }
    }
}