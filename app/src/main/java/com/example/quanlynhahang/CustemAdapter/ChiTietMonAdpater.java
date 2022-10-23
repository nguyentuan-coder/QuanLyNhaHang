package com.example.quanlynhahang.CustemAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlynhahang.DTO.MonAnDTO;
import com.example.quanlynhahang.R;

import java.util.List;
// hiển thị chi tiết các món ăn của loại món ăn
public class ChiTietMonAdpater extends BaseAdapter {
    Context context;
    int layout;
    List<MonAnDTO> monAnDTOList;
    ViewHolderChiTietMon ViewHolderChiTietMon;
    public ChiTietMonAdpater(Context context, int layout, List<MonAnDTO> monAnDTOList){
        this.context = context;
        this.layout = layout;
        this.monAnDTOList = monAnDTOList;

    }
    @Override
    public int getCount() {
        return monAnDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return monAnDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return monAnDTOList.get(i).getMaMonAn();
    }
    public class ViewHolderChiTietMon{
        ImageView imgHinhMon;
        TextView txtTenM,txtGiaTien;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ViewHolderChiTietMon = new ViewHolderChiTietMon();
            view = inflater.inflate(layout,viewGroup,false);
            ViewHolderChiTietMon.txtTenM = view.findViewById(R.id.txtDsTenMon);
            ViewHolderChiTietMon.imgHinhMon = view.findViewById(R.id.imgTenMon);
            ViewHolderChiTietMon.txtGiaTien = view.findViewById(R.id.txtGiaTien);
            view.setTag(ViewHolderChiTietMon);

        } else {
            ViewHolderChiTietMon = (ChiTietMonAdpater.ViewHolderChiTietMon) view.getTag();
        }
        MonAnDTO monAnDTO = monAnDTOList.get(i);
        ViewHolderChiTietMon.txtTenM.setText(monAnDTO.getTenMonAn());
        ViewHolderChiTietMon.txtGiaTien.setText("Giá: "+monAnDTO.getGiaTien());
        ViewHolderChiTietMon.imgHinhMon.setTag(i);
        return view;
    }
}
