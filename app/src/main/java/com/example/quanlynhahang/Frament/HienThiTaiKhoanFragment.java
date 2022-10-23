package com.example.quanlynhahang.Frament;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quanlynhahang.DangKyActitity;
import com.example.quanlynhahang.CustemAdapter.HienThiTaiKhoanAdapter;
import com.example.quanlynhahang.DAO.TaiKhoanDAO;
import com.example.quanlynhahang.DTO.TaiKhoanDTO;
import com.example.quanlynhahang.TrangchuActivity;
import com.example.quanlynhahang.R;

import java.util.List;

public class HienThiTaiKhoanFragment extends Fragment {
    ListView lvNhanVien;
    List<TaiKhoanDTO> dangKyDTOList;
    TaiKhoanDAO dangKyDAO;
    HienThiTaiKhoanAdapter adapteShowPeople;
    int maquyen;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_people,container,false);
        setHasOptionsMenu(true);
        ((TrangchuActivity)getActivity()).getSupportActionBar().setTitle(R.string.addPeople);
        lvNhanVien = view.findViewById(R.id.lvNhanVien);
        dangKyDAO = new TaiKhoanDAO(getActivity());
        HienThiDSNV();
        sharedPreferences = getActivity().getSharedPreferences("luuquyen", Context.MODE_PRIVATE);
        maquyen= sharedPreferences.getInt("maquyen",0);
        if (maquyen == 0){
            registerForContextMenu(lvNhanVien);
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (maquyen ==0){
            MenuItem menuItem = menu.add(1,R.id.itThemNhanVien,1,R.string.addPeople);
            menuItem.setIcon(R.drawable.addpeopl);
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itThemNhanVien){
            Intent intent = new Intent(getActivity(), DangKyActitity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
            getActivity().getMenuInflater().inflate(R.menu.edit_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vt = menuInfo.position;
        int ma= dangKyDTOList.get(vt).getMaTK();
        int id = item.getItemId();
        if (id == R.id.itSua){
            Intent intent = new Intent(getActivity(), DangKyActitity.class);
            intent.putExtra("manv",ma);
            startActivity(intent);

        } else if (id == R.id.itXoa){
          boolean kt= dangKyDAO.XoaTaiKhoan(ma);
          if (kt){
              HienThiDSNV();
              Toast.makeText(getActivity(), getResources().getString(R.string.successfuldelete), Toast.LENGTH_SHORT).show();
          } else {
              Toast.makeText(getActivity(), getResources().getString(R.string.deletefailed), Toast.LENGTH_SHORT).show();
          }
        }
        return super.onContextItemSelected(item);
    }
    public void HienThiDSNV(){
        dangKyDTOList= dangKyDAO.LayDSNhanVien();
        adapteShowPeople = new HienThiTaiKhoanAdapter(getActivity(),R.layout.custem_hienthitaikhoan,dangKyDTOList);
        lvNhanVien.setAdapter(adapteShowPeople);
        adapteShowPeople.notifyDataSetChanged();
    }
}
