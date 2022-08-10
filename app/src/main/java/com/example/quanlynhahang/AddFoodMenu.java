package com.example.quanlynhahang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.example.quanlynhahang.CustemAdapter.AdapterFoodType;
import com.example.quanlynhahang.DAO.FoodDAO;
import com.example.quanlynhahang.DAO.FoodTypeDAO;
import com.example.quanlynhahang.DTO.FoodDTO;
import com.example.quanlynhahang.DTO.FoodTypeDTO;

import java.util.List;
//thêm thực đơn

public class AddFoodMenu extends AppCompatActivity implements View.OnClickListener {
    ImageButton imgThemLoaiThucDon;
    Button btnImgae, btnDYThemMenu, btnHuyThemMenu;
    EditText edTenMonAn, edGiaTien;
    Spinner spLoaiThucDon;

    List<FoodTypeDTO> foodTypeDTOList;
    AdapterFoodType adapterShowMenu;
    ImageView imgHinhThucDon;
    FoodDAO foodDAO;
    FoodDTO foodDTO;
    FoodTypeDAO foodTypeDAO;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmenu);

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

        foodTypeDAO = new FoodTypeDAO(this);
        //foodDAO = new FoodDAO(this);

    }

    private void ShowMenuType() {
        foodTypeDTOList = foodTypeDAO.foodTypeDTOList();
        adapterShowMenu = new AdapterFoodType(AddFoodMenu.this, R.layout.custemsploaithucdon, foodTypeDTOList);
        spLoaiThucDon.setAdapter(adapterShowMenu);
        adapterShowMenu.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnXNThemMenu) {
//            int vt = spLoaiThucDon.getSelectedItemPosition();
//            int ml = foodTypeDTOList.get(vt).getMaLoai();
//            String sTenMonAn = edTenMonAn.getText().toString();
//            String sGiaTien = edGiaTien.getText().toString();
//            if (!sTenMonAn.isEmpty() || sGiaTien.isEmpty()) {
//                foodDTO = new FoodDTO();
//                foodDTO.setTenMonAn(sTenMonAn);
//                foodDTO.setGiaTien(sGiaTien);
//                foodDTO.setMaLoai(ml);
//                boolean kt = foodDAO.AddFood(foodDTO);
//                if (kt) {
//                    Toast.makeText(this, getResources().getString(R.string.addOk), Toast.LENGTH_SHORT).show();
//                    Log.d("kt",kt+"");
//                } else {
//                    Toast.makeText(this, getResources().getString(R.string.notadd), Toast.LENGTH_SHORT).show();
//                }
//            } else {
//
//                Toast.makeText(this, getResources().getString(R.string.x), Toast.LENGTH_SHORT).show();
//            }
            onBackPressed();
            //finish();
        } else if (id == R.id.btnHuyThemMenu) {
            //finish();
            onBackPressed();
        }
        if (id == R.id.imgThemLoaiThucDon) {
            Intent intent = new Intent(AddFoodMenu.this, AddMenuType.class);
            activityResultLauncher.launch(intent);
//            } else if (id == R.id.btnImgage){
////            Intent intentt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////            if (intentt.resolveActivity(getPackageManager())!= null){
////                activityResultLauncher.launch(intentt);
////            } else {
////                Toast.makeText(this, "Lỗi   ", Toast.LENGTH_SHORT).show();
////            }
//
//////            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//////            startActivityForResult(intent,3);
////            Intent intentt = new Intent();
////            intentt.setType("image/*");
////            intentt.setAction(Intent.ACTION_GET_CONTENT);
////            startActivityForResult(Intent.createChooser(intentt,"Chọn hình"),3);
//        }
        }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RESULT_OK && data !=null){
//                Uri selectiimage = data.getData();
//                Log.d("duongdan",data.getData()+"");
//                imgHinhThucDon.setImageURI(selectiimage);
//        }
//
//    }

    }
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() ==20 && result != null) {
                        Intent intent = result.getData();
                        intent.getStringExtra("tenloaithucdon");
                       // boolean kt = intent.getBooleanExtra("tenloaithucdon",false);
                       // Log.d("kt",kt+"");
                       // if (kt){
                            ShowMenuType();
                           // Toast.makeText(AddFoodMenu.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                      //  } else {
                           // Toast.makeText(AddFoodMenu.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                       // }

                    }
//            if (result.getResultCode() == RESULT_OK && result.getData() != null){
//                Bundle bundle = result.getData().getExtras();
//                Bitmap bitmap = (Bitmap) bundle.get("data");
//                imgHinhThucDon.setImageBitmap(bitmap);
//
//            }
                }
    });
}

