package com.example.quanlynhahang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynhahang.DAO.DangKyDAO;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edTenDN,edMKDN;
    Button btnDyDn,btnDangKy;
    DangKyDAO dangKyDAO;
    ImageView imgBack;
    CheckBox chkSavePass;
    String saveInformation = "login";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        edTenDN = findViewById(R.id.edTenDN);
        edMKDN = findViewById(R.id.edMatKhauĐN);
        chkSavePass = findViewById(R.id.chkSavePass);

        btnDyDn = findViewById(R.id.btnDyDN);
        btnDangKy = findViewById(R.id.btnDangKy);
        imgBack = findViewById(R.id.imgBack);

        btnDyDn.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
        imgBack.setOnClickListener(this);

        dangKyDAO = new DangKyDAO(this);
        //HienThiButtom();
    }

//    private void HienThiButtom() {
//        boolean kt = dangKyDAO.KiemTraTaiKhoan();
//        if (kt == true){
//            btnDangKy.setVisibility(View.GONE);
//            btnDyDn.setVisibility(View.INVISIBLE);
//        } else {
//            btnDangKy.setVisibility(View.GONE);
//            btnDyDn.setVisibility(View.GONE);
//        }
//    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnDyDN){
            btnDangNhap();
        } else if (id == R.id.btnDangKy){
            btnDangKy();
        } else if (id == R.id.imgBack){
            finish();
        }
    }
    private void btnDangNhap(){
        String sTaiK = edTenDN.getText().toString();
        String sMatK = edMKDN.getText().toString();
        boolean kt = dangKyDAO.KiemTraDangNhap(sTaiK,sMatK);
        if (kt){
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            intent.putExtra("tendangnhap",sTaiK);
            startActivity(intent);
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void btnDangKy(){
        Intent iDangKy = new Intent(this,ActivityRegister.class);
        iDangKy.putExtra("landau",1);
        startActivity(iDangKy);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences =getSharedPreferences(saveInformation,MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("sTaiKhoan",edTenDN.getText().toString()); // chuyền dữ liệu
        editor.putString("sMatKhau",edMKDN.getText().toString());
        editor.putBoolean("Save",chkSavePass.isChecked());
        editor.commit(); // xac nhan luu thong tin lai

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(saveInformation,MODE_PRIVATE);
        String sTk = preferences.getString("sTaiKhoan","");
        String sMk = preferences.getString("sMatKhau","");
        boolean save = preferences.getBoolean("Save",false);
        if (save){  // nếu chọn lưu sẽ hiện lên màn hình
            edTenDN.setText(sTk);
            edMKDN.setText(sMk);
        }
    }
}
