package com.example.quanlynhahang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quanlynhahang.DAO.BanAnDAO;
//dialog thêm bàn ăn

public class BanAnActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edNhapTenBanThem;
    Button btnXacNhanThem,btnHuyThem;
    BanAnDAO foodTableDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_banan);
        edNhapTenBanThem = findViewById(R.id.edNhapBanAnThem);
        btnXacNhanThem = findViewById(R.id.btnXNThem);
        btnHuyThem = findViewById(R.id.btnXNHuy);
        foodTableDAO = new BanAnDAO(this);
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