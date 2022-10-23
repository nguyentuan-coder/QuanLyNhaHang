package com.example.quanlynhahang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlynhahang.DAO.TaiKhoanDAO;
import com.example.quanlynhahang.DAO.QuyenDAO;
import com.example.quanlynhahang.DTO.TaiKhoanDTO;
import com.example.quanlynhahang.DTO.PhanQuyenDTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DangKyActitity extends AppCompatActivity implements View.OnClickListener {
    EditText edTaiKhoan, edMatKhau, edNgaySinh, edMaQuanLy;
    RadioButton raBtnNam, raBtnNu;
    RadioGroup rdGioiTinh;
    String sGioiTinh;
    Spinner spQuyen;
    TextView txtTieuDe;
    Button btnDongY, btnHuyBo;
    TaiKhoanDAO dangKyDAO;
    QuyenDAO quyenDAO;
    PhanQuyenDTO quyenDTO;
    List<PhanQuyenDTO> quyenDTOList;
    List<String>  dataTenQuyen;
    private DatePickerDialog piker;
    int manv;
    int landau=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        quyenDAO = new QuyenDAO(this);
        quyenDAO.ThemQuyen("quản lý");
        quyenDAO.ThemQuyen("nhân viên");
        manv = getIntent().getIntExtra("manv", 0);
        //landau = getIntent().getIntExtra("landau",0);

        quyenDTOList = quyenDAO.LayDanhSachQuyen();
        dataTenQuyen = new ArrayList<String>();
        for (int i=0;i<quyenDTOList.size();i++){
            String tenquyen = quyenDTOList.get(i).getTenQuyen();
            int vitrichon= quyenDTOList.get(i).getMaQuyen();
            Log.d("vitriduocchon",vitrichon+"");
            dataTenQuyen.add(tenquyen);}
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,dataTenQuyen);
            spQuyen.setAdapter(arrayAdapter);
            arrayAdapter.notifyDataSetChanged();
            //spQuyen.setVisibility(View.GONE);

        if (manv != 0) {
            txtTieuDe.setText(getResources().getString(R.string.updateNV));
            TaiKhoanDTO dangKyDTO = (TaiKhoanDTO) dangKyDAO.LayDSNhanVienTheoMa(manv);
            edTaiKhoan.setText(dangKyDTO.getTenDN());
            edMatKhau.setText(dangKyDTO.getmKhau());
            edNgaySinh.setText(dangKyDTO.getNgaySinh());
            edMaQuanLy.setText(dangKyDTO.getMaQuanLy());
            sGioiTinh = dangKyDTO.getGioiTinh();
//-------------------------------------------------------------------------------------
            if (sGioiTinh.equals("Nam")) {
                raBtnNam.setChecked(true);
            } else {
                raBtnNu.setChecked(true);
            }
        }
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
        TaiKhoanDTO dangKyDTO = new TaiKhoanDTO();
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
            TaiKhoanDTO dangKyDTO = new TaiKhoanDTO();
            dangKyDTO.setTenDN(sTaiKhoan);
            dangKyDTO.setmKhau(sMatKhau);
            dangKyDTO.setGioiTinh(sGioiTinh);
            dangKyDTO.setNgaySinh(sNgaySinh);
            dangKyDTO.setMaQuanLy(sMaQL);
                int vtri = spQuyen.getSelectedItemPosition();
                //int maquyen = quyenDTOList.get(vtri).getMaQuyen();
                dangKyDTO.setMaquyen(vtri);
            long kiemtra = dangKyDAO.DangKyTaiKhoan(dangKyDTO);
            if (kiemtra != 0) {
                Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent itDangNhap = new Intent(DangKyActitity.this, DangnhapActivity.class);
                startActivity(itDangNhap);
            } else {
                Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void addControls() {
        quyenDTO = new PhanQuyenDTO();
        dataTenQuyen = new ArrayList<String>();
        spQuyen = findViewById(R.id.spQuyen);
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
        quyenDAO = new QuyenDAO(this);
        dangKyDAO = new TaiKhoanDAO(this);
        btnDongY.setOnClickListener(this);
        btnHuyBo.setOnClickListener(this);
        edNgaySinh.setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull View view) {
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
    }
}

