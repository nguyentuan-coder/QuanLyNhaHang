package com.example.quanlynhahang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynhahang.DAO.FoodTypeDAO;

// dialog theem loại thực đơn

public class AddMenuType extends AppCompatActivity implements View.OnClickListener {
    EditText edNhapLoaiTD;
    Button btnXNThemLTD,btnHuyThemLTD;
    FoodTypeDAO foodTypeDAO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menutype);
        edNhapLoaiTD = findViewById(R.id.edNhapLoaiTD);
        btnXNThemLTD = findViewById(R.id.btnXNThemLTD);
        btnHuyThemLTD = findViewById(R.id.btnHuyThemLTD);

        btnXNThemLTD.setOnClickListener(this);
        btnHuyThemLTD.setOnClickListener(this);
        foodTypeDAO = new FoodTypeDAO(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnXNThemLTD){
            String sLDT = edNhapLoaiTD.getText().toString();
            if ( ! sLDT.isEmpty()){
                boolean kt =  foodTypeDAO.ThemLoaiMonAn(sLDT);
                    Intent intent = new Intent();
                    intent.putExtra("tenloaithucdon",sLDT);
                    setResult(20,intent);
                    finish();
                   // onBackPressed();
            } else {
                Toast.makeText(this, getResources().getString(R.string.x), Toast.LENGTH_SHORT).show();
            }
        } if (id == R.id.btnHuyThemLTD){
            finish();
        }

    }
}
