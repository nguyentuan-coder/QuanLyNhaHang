package com.example.quanlynhahang.CustemAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlynhahang.DAO.DangKyDAO;
import com.example.quanlynhahang.DTO.DangKyDTO;
import com.example.quanlynhahang.R;

import java.util.List;

public class AdapteShowPeople extends BaseAdapter {
    Context context;
    int layout;
    List<DangKyDTO> dangKyDTOList;
    ViewHolder viewHolder;

    public AdapteShowPeople(Context context, int layout, List<DangKyDTO> dangKyDTOList){
        this.context=context;
        this.layout=layout;
        this.dangKyDTOList=dangKyDTOList;
    }

    @Override
    public int getCount() {
        return dangKyDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return dangKyDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dangKyDTOList.get(i).getMaTK();
    }
    public class ViewHolder{
        ImageView imgPeople;
        TextView txtTenNhanVienn,txtMQL;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,viewGroup,false);
            viewHolder.imgPeople = view.findViewById(R.id.imgPeople);
            viewHolder.txtTenNhanVienn = view.findViewById(R.id.txtTenNhanVien);
            viewHolder.txtMQL = view.findViewById(R.id.txtMaQl);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        DangKyDTO dangKyDTO = dangKyDTOList.get(i);
        viewHolder.txtTenNhanVienn.setText(dangKyDTO.getTenDN());
        viewHolder.txtMQL.setText(dangKyDTO.getMaQuanLy());
        return view;
    }
}
