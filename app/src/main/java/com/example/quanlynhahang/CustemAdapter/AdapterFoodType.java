package com.example.quanlynhahang.CustemAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlynhahang.DTO.FoodTableDTO;
import com.example.quanlynhahang.DTO.FoodTypeDTO;
import com.example.quanlynhahang.R;

import java.util.List;

public class AdapterFoodType extends BaseAdapter{
    Context context;
    List<FoodTypeDTO> foodTypeDTOList;
    int layout;
    ViewHolderLoaiMonAN viewHolderLoaiMonAN;
    public AdapterFoodType(Context context, int layout, List<FoodTypeDTO> list){
        this.context=context;
        this.layout=layout;
        this.foodTypeDTOList=list;
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

    public class ViewHolderLoaiMonAN{
        TextView txtSpTenLoai;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            viewHolderLoaiMonAN = new ViewHolderLoaiMonAN();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custemsploaithucdon,viewGroup,false);
            viewHolderLoaiMonAN.txtSpTenLoai = view.findViewById(R.id.txtSpTenLoai);
            view.setTag(viewHolderLoaiMonAN);
        } else {
            viewHolderLoaiMonAN = (ViewHolderLoaiMonAN) view.getTag();
        }
        FoodTypeDTO foodTypeDTO = foodTypeDTOList.get(i);
        viewHolderLoaiMonAN.txtSpTenLoai.setText(foodTypeDTO.getTenLoai());
        viewHolderLoaiMonAN.txtSpTenLoai.setTag(foodTypeDTO.getMaLoai());
        return view;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            viewHolderLoaiMonAN = new ViewHolderLoaiMonAN();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.custemsploaithucdon,parent,false);
            viewHolderLoaiMonAN.txtSpTenLoai = view.findViewById(R.id.txtSpTenLoai);
            view.setTag(viewHolderLoaiMonAN);
        } else {
            viewHolderLoaiMonAN = (ViewHolderLoaiMonAN)view.getTag();
        }
        FoodTypeDTO foodTypeDTO = foodTypeDTOList.get(position);
        viewHolderLoaiMonAN.txtSpTenLoai.setText(foodTypeDTO.getTenLoai());
        viewHolderLoaiMonAN.txtSpTenLoai.setTag(foodTypeDTO.getMaLoai());
        return view;
    }

}
