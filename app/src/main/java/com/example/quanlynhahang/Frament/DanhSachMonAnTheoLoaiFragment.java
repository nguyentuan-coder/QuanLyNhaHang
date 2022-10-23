package com.example.quanlynhahang.Frament;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.quanlynhahang.CustemAdapter.ChiTietMonAdpater;
import com.example.quanlynhahang.DAO.MonAnDAO;
import com.example.quanlynhahang.DTO.MonAnDTO;
import com.example.quanlynhahang.R;

import java.util.List;

public class DanhSachMonAnTheoLoaiFragment extends Fragment {
    GridView gridView;
    MonAnDAO monAnDAO;
    List<MonAnDTO> monAnDTOList;
    ChiTietMonAdpater chiTietMonAdpater;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.graddmenu,container,false);
        gridView = view.findViewById(R.id.grShowFoodMenu);
        monAnDAO = new MonAnDAO(getActivity());

        Bundle bundle = getArguments();
        if (bundle != null){
            int maloai = bundle.getInt("maloai");
            monAnDTOList = monAnDAO.LayDSMonTheoLoai(maloai);
            Log.d("monan",monAnDTOList.size()+"");

            //adapterShowFoodTypeFlMenu = new AdapterShowFoodTypeFlMenu(getActivity(),R.layout.custem_showfoodtype,foodDTOList);
            chiTietMonAdpater = new ChiTietMonAdpater(getActivity(),R.layout.custemlayoutchitietmon,monAnDTOList);
            gridView.setAdapter(chiTietMonAdpater);
            chiTietMonAdpater.notifyDataSetChanged();
        }
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    getFragmentManager().popBackStack("hienthiloai", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                return false;
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
