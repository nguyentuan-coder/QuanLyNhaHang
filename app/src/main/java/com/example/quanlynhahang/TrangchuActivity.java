package com.example.quanlynhahang;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.quanlynhahang.Frament.HienThiBanAnFragment;
import com.example.quanlynhahang.Frament.HienThiDSMonAnFragment;
import com.example.quanlynhahang.Frament.HienThiTaiKhoanFragment;
import com.google.android.material.navigation.NavigationView;

public class TrangchuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Toolbar toolBar;
    FrameLayout frameLayout;
    TextView txtTenNhanVien;
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    NavigationView navigationView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_page);
        addControls();
        addEvents();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HienThiBanAnFragment hienThiBanAn = new HienThiBanAnFragment();
        fragmentTransaction.replace(R.id.frameLayout,hienThiBanAn);
        fragmentTransaction.commit();
    }
    private void addEvents() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolBar.setTitle("Quản Lý Nhà Hàng");
        toolBar.setNavigationIcon(R.drawable.menu);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        navigationView.setItemIconTintList(null);
        Intent intent = getIntent();
        String sTenNV = intent.getStringExtra("tendangnhap");
        txtTenNhanVien.setText(sTenNV);
    }

    private void setSupportActionBar(Toolbar toolBar) {
    }


    private void addControls() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolBar = findViewById(R.id.toolBar);
        navigationView = findViewById(R.id.navigationView);
        View v = navigationView.getHeaderView(0);
        txtTenNhanVien = v.findViewById(R.id.txtTenNhanVien);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        frameLayout = findViewById(R.id.frameLayout);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.idTrangTru){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HienThiBanAnFragment hienThiBanAn = new HienThiBanAnFragment();
            fragmentTransaction.replace(R.id.frameLayout,hienThiBanAn);
            fragmentTransaction.commit();
            item.setChecked(true);
            drawerLayout.closeDrawers();
        }
        if (id == R.id.idThucDon){
            FragmentTransaction fragmentTransactionThucDon = fragmentManager.beginTransaction();
            HienThiDSMonAnFragment hienThiThucDon = new HienThiDSMonAnFragment();
            fragmentTransactionThucDon.replace(R.id.frameLayout,hienThiThucDon);
            fragmentTransactionThucDon.commit();
            item.setChecked(true);
            drawerLayout.closeDrawers();
        }if (id == R.id.idNhanVien){
            FragmentTransaction fragmentTransactionPeople = fragmentManager.beginTransaction();
            HienThiTaiKhoanFragment hienNhanVien = new HienThiTaiKhoanFragment();
            fragmentTransactionPeople.replace(R.id.frameLayout,hienNhanVien);
            fragmentTransactionPeople.commit();
            item.setChecked(true);
            drawerLayout.closeDrawers();
        }
        return false;
    }
}
