package com.example.quanlynhahang.CustemAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlynhahang.DTO.BanAnDTO;
import com.example.quanlynhahang.R;

import java.util.List;

public class HienThiBanAnAdapter extends BaseAdapter implements View.OnClickListener{
    Context context;
    int layout;
    List<BanAnDTO> foodTableDTOList;
    ViewHolder viewHolder;
    public HienThiBanAnAdapter(Context context, int layout, List<BanAnDTO> foodTableDTOList){
        this.context = context;
        this.layout = layout;
        this.foodTableDTOList = foodTableDTOList;
    }
    @Override
    public int getCount() {
        return foodTableDTOList.size();
    }

    @Override
    public Object getItem(int i) {
        return foodTableDTOList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return foodTableDTOList.get(i).getMaBan();
    }



    private class ViewHolder {
        ImageView imgFoodTable,imgOderFood,imgPay,imgShowTable;
        TextView txtNameTable;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.custom_banan,viewGroup, false);
            viewHolder.imgFoodTable = view.findViewById(R.id.imgFoodTable);
            viewHolder.imgOderFood =view.findViewById(R.id.imgOderFood);
            viewHolder.imgPay = view.findViewById(R.id.imgPay);
            viewHolder.imgShowTable = view.findViewById(R.id.imgShowTable);
            viewHolder.txtNameTable = view.findViewById(R.id.txtTenBanAn);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (foodTableDTOList.get(i).isDuocchon()){
            ShowButtom();
        } else {
            HideButtom();
        }

        BanAnDTO foodTableDTO = foodTableDTOList.get(i);
        viewHolder.txtNameTable.setText(foodTableDTO.getTenBan());
        viewHolder.imgFoodTable.setTag(i);
        viewHolder.imgFoodTable.setOnClickListener(this);
        viewHolder.imgShowTable.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        viewHolder = (ViewHolder) ((View)view.getParent()).getTag();
        if (id == R.id.imgFoodTable){
            String tablename = viewHolder.txtNameTable.getText().toString();
            int vt = (int) view.getTag();
            foodTableDTOList.get(vt).setDuocchon(true);
            ShowButtom();
        }
        if (id == R.id.imgShowTable){
            HideButtom();
        }
    }

    private void ShowButtom(){
        viewHolder.imgOderFood.setVisibility(View.VISIBLE);
        viewHolder.imgPay.setVisibility(View.VISIBLE);
        viewHolder.imgShowTable.setVisibility(View.VISIBLE);

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.hieuunghienthibuttombanan);
        viewHolder.imgFoodTable.startAnimation(animation);
        viewHolder.imgOderFood.startAnimation(animation);
        viewHolder.imgShowTable.startAnimation(animation);
    }
    private void HideButtom(){
        viewHolder.imgOderFood.setVisibility(View.INVISIBLE);
        viewHolder.imgPay.setVisibility(View.INVISIBLE);
        viewHolder.imgShowTable.setVisibility(View.INVISIBLE);

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.anhienbuttombanan);
        viewHolder.imgFoodTable.startAnimation(animation);
        viewHolder.imgOderFood.startAnimation(animation);
        viewHolder.imgShowTable.startAnimation(animation);
    }
}
