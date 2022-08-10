package com.example.quanlynhahang.CustemAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlynhahang.DTO.FoodTypeDTO;
import com.example.quanlynhahang.R;

import java.util.List;

public class AdapterShowFoodTypeFlMenu extends BaseAdapter {
    int layout;
    Context context;
    List<FoodTypeDTO> foodTypeDTOList;
    ViewHolder viewHolder;
    public AdapterShowFoodTypeFlMenu(Context context,int layout, List<FoodTypeDTO> foodTypeDTOList){
        this.context = context;
        this.layout=layout;
        this.foodTypeDTOList=foodTypeDTOList;

    }
    @Override
    public int getCount() {
        return foodTypeDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodTypeDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return foodTypeDTOList.get(i).getMaLoai();
    }
    public class ViewHolder{
        TextView txtTenLoaiMA;
        ImageView imgFoodType;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,viewGroup,false);
            viewHolder.txtTenLoaiMA=view.findViewById(R.id.txtLoaiMonAN);
            viewHolder.imgFoodType=view.findViewById(R.id.imgFoodType);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        FoodTypeDTO foodTypeDTO = new FoodTypeDTO();
        viewHolder.txtTenLoaiMA.setText(foodTypeDTO.getTenLoai());
        viewHolder.imgFoodType.setTag(i);
        return view;
    }
}
