package com.example.quanlynhahang.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quanlynhahang.AddFoodMenu;
import com.example.quanlynhahang.CustemAdapter.AdapterShowFoodTypeFlMenu;
import com.example.quanlynhahang.DAO.FoodTypeDAO;
import com.example.quanlynhahang.DTO.FoodTypeDTO;
import com.example.quanlynhahang.HomeActivity;
import com.example.quanlynhahang.R;

import java.util.List;
// hiện danh sách thực đơn

public class ShowMenuFrame extends Fragment {
    GridView grShowFoodMenu;
    List<FoodTypeDTO> foodTypeDTOList;
    FoodTypeDAO foodTypeDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graddmenu,container,false);
        setHasOptionsMenu(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle(R.string.menu);
        grShowFoodMenu = view.findViewById(R.id.grShowFoodMenu);
        foodTypeDAO = new FoodTypeDAO(getActivity());
        foodTypeDTOList = foodTypeDAO.foodTypeDTOList();
        AdapterShowFoodTypeFlMenu adapterShowFoodTypeFlMenu  = new AdapterShowFoodTypeFlMenu(getActivity(),R.layout.custem_showfoodtype,foodTypeDTOList);
        grShowFoodMenu.setAdapter(adapterShowFoodTypeFlMenu);
        adapterShowFoodTypeFlMenu.notifyDataSetChanged();
        return view;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.add(1,R.id.itThemThucDon,1,R.string.addmenu);
        menuItem.setIcon(R.drawable.addmenu);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itThemThucDon){
            Intent intent = new Intent(getActivity(), AddFoodMenu.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
