package com.example.quanlynhahang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhahang.DAO.DangKyDAO;
import com.example.quanlynhahang.DTO.DangKyDTO;

import java.util.Calendar;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener {
    EditText edTaiKhoan, edMatKhau, edNgaySinh, edMaQuanLy;
    RadioButton raBtnNam, raBtnNu;
    RadioGroup rdGioiTinh;
    String sGioiTinh;
    TextView txtTieuDe;
    Button btnDongY, btnHuyBo;
    DangKyDAO dangKyDAO;
    private DatePickerDialog piker;
    int manv;

    int landau =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        manv = getIntent().getIntExtra("manv", 0);
        if (manv != 0) {
            txtTieuDe.setText(getResources().getString(R.string.updateNV));
            DangKyDTO dangKyDTO = (DangKyDTO) dangKyDAO.LayDSNhanVienTheoMa(manv);
            edTaiKhoan.setText(dangKyDTO.getTenDN());
            edMatKhau.setText(dangKyDTO.getmKhau());
            edNgaySinh.setText(dangKyDTO.getNgaySinh());
            edMaQuanLy.setText(dangKyDTO.getMaQuanLy());
            sGioiTinh = dangKyDTO.getGioiTinh();

            // kiểm tra lần đầu đăgn nhập
            landau = getIntent().getIntExtra("landau",0);


            if (sGioiTinh.equals("Nam")) {
                raBtnNam.setChecked(true);
            } else {
                raBtnNu.setChecked(true);
            }
        }
    }

    private void addEvents() {

//        edXacNhanLaiMatKhau.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }

//            @Override
//            public void afterTextChanged(Editable editable) {
//                String passwrd  = edMatKhau.getText().toString();
//                if (editable.length() > 0 && passwrd.length() >0){
//                    if (! edXacNhanLaiMatKhau.equals(passwrd)){
//                        Toast.makeText(ActivityRegister.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(ActivityRegister.this, "Mật khẩu trùng", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//            }
//        });
    }

    public void SuaNhanVien() {
        String sTaiKhoan = edTaiKhoan.getText().toString();
        String sMatKhau = edMatKhau.getText().toString();
        String sNgaySinh = edNgaySinh.getText().toString();
        String sMaQL = edMaQuanLy.getText().toString();
        switch (rdGioiTinh.getCheckedRadioButtonId()) {
            case R.id.rdNam:
                sGioiTinh = "Nam";
                break;
            case R.id.rdNu:
                sGioiTinh = "Nu";
                break;
        }
        DangKyDTO dangKyDTO = new DangKyDTO();
        dangKyDTO.setMaTK(manv);
        dangKyDTO.setTenDN(sTaiKhoan);
        dangKyDTO.setmKhau(sMatKhau);
        dangKyDTO.setGioiTinh(sGioiTinh);
        dangKyDTO.setNgaySinh(sNgaySinh);
        dangKyDTO.setMaQuanLy(sMaQL);
        boolean kiemtra = dangKyDAO.SuaNV(dangKyDTO);
        if (kiemtra) {
            Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void DYThemNV(){
        String sTaiKhoan = edTaiKhoan.getText().toString();
        String sMatKhau = edMatKhau.getText().toString();
        switch (rdGioiTinh.getCheckedRadioButtonId()) {
            case R.id.rdNam:
                sGioiTinh = "Nam";
                break;
            case R.id.rdNu:
                sGioiTinh = "Nu";
                break;
        }
        String sNgaySinh = edNgaySinh.getText().toString();
        String sMaQL = edMaQuanLy.getText().toString();
        if (sTaiKhoan.isEmpty()) {
            Toast.makeText(this, "Bạn chưa điền tài khoản", Toast.LENGTH_SHORT).show();
        } else if (sMatKhau.isEmpty()) {
            Toast.makeText(this, "Bạn chưa điền mật khẩu", Toast.LENGTH_SHORT).show();
        } else if (sMaQL.isEmpty()) {
            Toast.makeText(this, "Bạn chưa điền mã quản lý", Toast.LENGTH_SHORT).show();
        } else {
            DangKyDTO dangKyDTO = new DangKyDTO();
            dangKyDTO.setTenDN(sTaiKhoan);
            dangKyDTO.setmKhau(sMatKhau);
            dangKyDTO.setGioiTinh(sGioiTinh);
            dangKyDTO.setNgaySinh(sNgaySinh);
            dangKyDTO.setMaQuanLy(sMaQL);

            long kiemtra = dangKyDAO.DangKyTaiKhoan(dangKyDTO);
            if (kiemtra != 0) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent itDangNhap = new Intent(ActivityRegister.this, LoginActivity.class);
                startActivity(itDangNhap);

            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void addControls() {
        // ánh xạ
        edTaiKhoan = findViewById(R.id.edTaiKhoan);
        edMatKhau = findViewById(R.id.edMatKhau);
        edNgaySinh = findViewById(R.id.edNgaySinh);
        edMaQuanLy = findViewById(R.id.edMaQL);
        btnDongY = findViewById(R.id.btnDongy);
        btnHuyBo = findViewById(R.id.btnHuyBo);
        raBtnNam = findViewById(R.id.rdNam);
        raBtnNu = findViewById(R.id.rdNu);
        txtTieuDe = findViewById(R.id.txtTieuDe);
        rdGioiTinh = findViewById(R.id.rdGioitinh);


        dangKyDAO = new DangKyDAO(this);
        btnDongY.setOnClickListener(this);
        btnHuyBo.setOnClickListener(this);
        edNgaySinh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnDongy:
                if (manv != 0){
                    SuaNhanVien();

                } else {
                    DYThemNV();
                }

                break;
            case R.id.btnHuyBo:
                finish();
                break;
            case R.id.edNgaySinh:
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                piker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String sNgaySinh = i + "/" + i1 + "/" + i2;
                        edNgaySinh.setText(sNgaySinh);
                    }
                }, year, month, day);
                piker.show();break;
        }
        //thuc hien them moi nhan vien
//            if(id == R.id.btnDongy){
//                String sTaiKhoan = edTaiKhoan.getText().toString();
//                String sMatKhau = edMatKhau.getText().toString();
//                switch (rdGioiTinh.getCheckedRadioButtonId()){
//                    case R.id.rdNam:
//                        sGioiTinh ="Nam";
//                        break;
//                    case R.id.rdNu:
//                        sGioiTinh = "Nu";
//                        break;
//                }
//                String sNgaySinh = edNgaySinh.getText().toString();
//                String sMaQL= edMaQuanLy.getText().toString();
//                if(sTaiKhoan.isEmpty()){
//                    Toast.makeText(this, "Bạn chưa điền tài khoản", Toast.LENGTH_SHORT).show();
//                } else if(sMatKhau.isEmpty()) {
//                    Toast.makeText(this, "Bạn chưa điền mật khẩu", Toast.LENGTH_SHORT).show();
//                } else if(sMaQL.isEmpty()){
//                    Toast.makeText(this, "Bạn chưa điền mã quản lý", Toast.LENGTH_SHORT).show();
//                } else {
//                    DangKyDTO dangKyDTO = new DangKyDTO();
//                    dangKyDTO.setTenDN(sTaiKhoan);
//                    dangKyDTO.setmKhau(sMatKhau);
//                    dangKyDTO.setGioiTinh(sGioiTinh);
//                    dangKyDTO.setNgaySinh(sNgaySinh);
//                    dangKyDTO.setMaQuanLy(sMaQL);
//
//                    long kiemtra = dangKyDAO.DangKyTaiKhoan(dangKyDTO);
//                    if (kiemtra !=0){
//                        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                        Intent itDangNhap = new Intent(ActivityRegister.this,LoginActivity.class);
//                        startActivity(itDangNhap);
//                    } else {
//                        Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                finish();
//            } else if( id == R.id.btnHuyBo) {
//                finish();
//            } else if (id == R.id.edNgaySinh) {
//                final Calendar calendar = Calendar.getInstance();
//                int day = calendar.get(Calendar.DAY_OF_MONTH);
//                int month = calendar.get(Calendar.MONTH);
//                int year = calendar.get(Calendar.YEAR);
//                piker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                        String sNgaySinh = i+"/"+i1+"/"+i2;
//                        edNgaySinh.setText(sNgaySinh);
//                    }
//                }, year, month, day);
//                piker.show();
//            }
    }
}

