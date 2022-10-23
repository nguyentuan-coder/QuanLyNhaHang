package com.example.quanlynhahang.Frament;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.quanlynhahang.ThemMonAnActivity;
import com.example.quanlynhahang.CustemAdapter.HienThiLoaiMonAnAdapter;
import com.example.quanlynhahang.DAO.LoaiMonAnDAO;
import com.example.quanlynhahang.DTO.LoaiMonAnDTO;
import com.example.quanlynhahang.TrangchuActivity;
import com.example.quanlynhahang.R;

import java.util.List;
// hiện danh sách thực đơn

public class HienThiDSMonAnFragment extends Fragment {
    GridView grShowFoodMenu;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    LoaiMonAnDAO loaiMonAnDAO;
    int maquyen =0;
    FragmentManager fragmentManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graddmenu,container,false);
        setHasOptionsMenu(true);
        ((TrangchuActivity)getActivity()).getSupportActionBar().setTitle(R.string.menu);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maquyen = sharedPreferences.getInt("maquyen",0);
        grShowFoodMenu = view.findViewById(R.id.grShowFoodMenu);
        loaiMonAnDAO  = new LoaiMonAnDAO(getActivity());
        loaiMonAnDTOList = loaiMonAnDAO.foodTypeDTOList();
//        HienThiLoaiMonAnAdapter adapter = new HienThiLoaiMonAnAdapter(getActivity(),R.layout.custem_showfoodtype,foodTypeDTOList);
//        grShowFoodMenu.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        HienThiLoaiMonAnAdapter adapter  = new HienThiLoaiMonAnAdapter(getActivity(),R.layout.custemlayoutchitietmon,loaiMonAnDTOList);
        grShowFoodMenu.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        fragmentManager = getActivity().getSupportFragmentManager();
        grShowFoodMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int maloai = loaiMonAnDTOList.get(i).getMaLoai();
                DanhSachMonAnTheoLoaiFragment showListMonAn = new DanhSachMonAnTheoLoaiFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("maloai",maloai);
                showListMonAn.setArguments(bundle);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                FragmentManager m = getActivity().getSupportFragmentManager();
//                m.beginTransaction().replace(R.id.frameLayout,showListMonAn).addToBackStack("hienthiloai");
                fragmentTransaction.replace(R.id.frameLayout,showListMonAn).addToBackStack("hienthiloai");
                //fragmentTransaction.replace(R.id.frameLayout,showListMonAn);
                adapter.notifyDataSetChanged();
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (maquyen == 0){
            MenuItem menuItem = menu.add(1,R.id.itThemThucDon,1,R.string.addmenu);
            menuItem.setIcon(R.drawable.addmenu);
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itThemThucDon){
            Intent intent = new Intent(getActivity(), ThemMonAnActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
