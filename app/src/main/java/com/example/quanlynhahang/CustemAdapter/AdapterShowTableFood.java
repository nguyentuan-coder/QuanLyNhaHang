package com.example.quanlynhahang.CustemAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlynhahang.DTO.FoodTableDTO;
import com.example.quanlynhahang.R;

import java.util.List;

public class AdapterShowTableFood extends BaseAdapter implements View.OnClickListener{
    Context context;
    int layout;
    List<FoodTableDTO> foodTableDTOList;
    ViewHolder viewHolder;
    public AdapterShowTableFood(Context context,int layout,List<FoodTableDTO> foodTableDTOList){
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
            view = inflater.inflate(R.layout.custemfoodtable,viewGroup, false);
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

        FoodTableDTO foodTableDTO = foodTableDTOList.get(i);
        viewHolder.txtNameTable.setText(foodTableDTO.getTenBan());
        viewHolder.imgFoodTable.setTag(i);
        viewHolder.imgFoodTable.setOnClickListener(this);

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
    }
    private void ShowButtom(){
        viewHolder.imgOderFood.setVisibility(View.VISIBLE);
        viewHolder.imgPay.setVisibility(View.VISIBLE);
        viewHolder.imgShowTable.setVisibility(View.VISIBLE);
    }
    private void HideButtom(){
        viewHolder.imgOderFood.setVisibility(View.INVISIBLE);
        viewHolder.imgPay.setVisibility(View.INVISIBLE);
        viewHolder.imgShowTable.setVisibility(View.INVISIBLE);
    }
}
