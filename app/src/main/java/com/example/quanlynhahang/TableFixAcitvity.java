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

import com.example.quanlynhahang.DAO.FoodTableDAO;
// dialog sửa bàn ăn
public class TableFixAcitvity extends AppCompatActivity implements View.OnClickListener {
    Button btnDongYSua, btnHuySua;
    EditText edTenBanSua;
    FoodTableDAO foodTableDAO;
    int maban;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablefix);
        btnDongYSua = findViewById(R.id.btnXNSua);
        btnHuySua = findViewById(R.id.btnHuySua);
        edTenBanSua = findViewById(R.id.edNhapBanAnSua);
        foodTableDAO = new FoodTableDAO(this);

        btnDongYSua.setOnClickListener(this);
        btnHuySua.setOnClickListener(this);
        maban = getIntent().getIntExtra("maban",0);
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        String tenban = edTenBanSua.getText().toString();
        if (id == R.id.btnXNSua){
            if (! tenban.isEmpty()){
              boolean kt =  foodTableDAO.UpdateTenBan(maban,tenban);
                Intent intent = new Intent();
                intent.putExtra("kiemtra",kt);
                setResult(99,intent);
                finish();
            } else {
                Toast.makeText(this, getResources().getString(R.string.x), Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.btnHuySua){
            finish();
        }
    }
}
