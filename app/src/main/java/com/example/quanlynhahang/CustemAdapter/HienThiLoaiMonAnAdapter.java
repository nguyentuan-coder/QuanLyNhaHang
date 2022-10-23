package com.example.quanlynhahang.CustemAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlynhahang.DTO.LoaiMonAnDTO;
import com.example.quanlynhahang.DTO.MonAnDTO;
import com.example.quanlynhahang.R;

import java.util.List;

public class HienThiLoaiMonAnAdapter extends BaseAdapter {
    int layout;
    Context context;
    List<LoaiMonAnDTO> loaiMonAnDTOList;
    ViewHolder viewHolder;
    public HienThiLoaiMonAnAdapter(Context context, int layout, List<LoaiMonAnDTO> loaiMonAnDTOList){
        this.context = context;
        this.layout=layout;
        this.loaiMonAnDTOList=loaiMonAnDTOList;

    }
    @Override
    public int getCount() {
        return loaiMonAnDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return loaiMonAnDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return loaiMonAnDTOList.get(i).getMaLoai();
    }
    public class ViewHolder{
        TextView txtTenMonAn;
        ImageView imgHinhAnh;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.custom_hienthiloaimonan,viewGroup,false);
            viewHolder.txtTenMonAn=view.findViewById(R.id.txtTenMon);
            viewHolder.imgHinhAnh=view.findViewById(R.id.imgHumburger);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        LoaiMonAnDTO loaiMonAnDTO = loaiMonAnDTOList.get(i);
        //int maloai = loaiMonAnDTO.getMaLoai();
        viewHolder.txtTenMonAn.setText(loaiMonAnDTO.getTenLoai());
        viewHolder.imgHinhAnh.setTag(i);

        return view;
    }
}
