package com.example.quanlynhahang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlynhahang.CustemAdapter.SpinerLoaiMonAnAdapter;
import com.example.quanlynhahang.DAO.MonAnDAO;
import com.example.quanlynhahang.DAO.LoaiMonAnDAO;
import com.example.quanlynhahang.DTO.MonAnDTO;
import com.example.quanlynhahang.DTO.LoaiMonAnDTO;

import java.util.List;
//thêm thực đơn

public class ThemMonAnActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton imgThemLoaiThucDon;
    Button btnImgae, btnDYThemMenu, btnHuyThemMenu;
    EditText edTenMonAn, edGiaTien;
    Spinner spLoaiThucDon;

    List<LoaiMonAnDTO> foodTypeDTOList;
    SpinerLoaiMonAnAdapter adapterShowMenu;
    ImageView imgHinhThucDon;
    MonAnDAO foodDAO;
    MonAnDTO foodDTO;
    LoaiMonAnDAO foodTypeDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_menu);

        // btnImgae = findViewById(R.id.btnImgage);
        imgThemLoaiThucDon = findViewById(R.id.imgThemLoaiThucDon);
        spLoaiThucDon = findViewById(R.id.spLoaiThucDon);
        btnDYThemMenu = findViewById(R.id.btnXNThemMenu);
        btnHuyThemMenu = findViewById(R.id.btnHuyThemMenu);
        edTenMonAn = findViewById(R.id.edTenMonAn);
        edGiaTien = findViewById(R.id.edGiaTien);
        imgHinhThucDon = findViewById(R.id.imgHinhThucDon);

        btnDYThemMenu.setOnClickListener(this);
        btnHuyThemMenu.setOnClickListener(this);
        //btnImgae.setOnClickListener(this);
        imgHinhThucDon.setOnClickListener(this);
        imgThemLoaiThucDon.setOnClickListener(this);

        foodTypeDAO = new LoaiMonAnDAO(this);
        foodDAO = new MonAnDAO(this);

    }

    private void ShowMenuType() {
        foodTypeDTOList = foodTypeDAO.foodTypeDTOList();
        adapterShowMenu = new SpinerLoaiMonAnAdapter(ThemMonAnActivity.this, R.layout.custemsploaithucdon, foodTypeDTOList);
        spLoaiThucDon.setAdapter(adapterShowMenu);
        adapterShowMenu.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnXNThemMenu) {
            int vt = spLoaiThucDon.getSelectedItemPosition();
            int ml = foodTypeDTOList.get(vt).getMaLoai();
            String sTenMonAn = edTenMonAn.getText().toString();
            String sGiaTien = edGiaTien.getText().toString();
            if (!sTenMonAn.isEmpty() || sGiaTien.isEmpty()) {
                foodDTO = new MonAnDTO();
                foodDTO.setTenMonAn(sTenMonAn);
                foodDTO.setGiaTien(sGiaTien);
                foodDTO.setMaLoai(ml);
                boolean kt = foodDAO.AddFood(foodDTO);
                if (kt) {
                    Toast.makeText(this, getResources().getString(R.string.addOk), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.notadd), Toast.LENGTH_SHORT).show();
                }
               onBackPressed();
            } else {
                Toast.makeText(this, getResources().getString(R.string.x), Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.btnHuyThemMenu) {
            onBackPressed();
        }
        if (id == R.id.imgThemLoaiThucDon) {
            Intent intent = new Intent(ThemMonAnActivity.this, ThemThucDonActivity.class);
            activityResultLauncher.launch(intent);
        }


    }
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() ==20 && result != null) {
                        Intent intent = result.getData();
                       boolean kt = intent.getBooleanExtra("tenloaithucdon",false);
                       if (kt){
                            Toast.makeText(ThemMonAnActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            ShowMenuType();
                       } else {
                            Toast.makeText(ThemMonAnActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
    });
}

