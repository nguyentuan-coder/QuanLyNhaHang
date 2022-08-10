package com.example.quanlynhahang.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quanlynhahang.CustemAdapter.AdapterShowTableFood;
import com.example.quanlynhahang.DAO.FoodTableDAO;
import com.example.quanlynhahang.DTO.FoodTableDTO;
import com.example.quanlynhahang.FoodTableActivity;
import com.example.quanlynhahang.HomeActivity;
import com.example.quanlynhahang.R;
import com.example.quanlynhahang.TableFixAcitvity;

import java.util.List;
// hiển thị bàn ăn
public class ShowFoobTable extends Fragment {
    GridView gridViewShowFoodTable;
    List<FoodTableDTO> foodTableDTOList;
    FoodTableDAO foodTableDAO;
    AdapterShowTableFood adapterShowTableFood;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.showfoodtable,container,false);
        setHasOptionsMenu(true);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle(R.string.table);
        gridViewShowFoodTable = view.findViewById(R.id.grvShowFoodTable);
        foodTableDAO = new FoodTableDAO(getActivity());
        registerForContextMenu(gridViewShowFoodTable);
        ShowList();
        return view;
    }
    //-----------------------------
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.edit_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int vt = menuInfo.position;
        int maban = foodTableDTOList.get(vt).getMaBan();
        int id = item.getItemId();
        // sửa bàn ăn
        if (id == R.id.itSua){
            Intent intent = new Intent(getActivity(), TableFixAcitvity.class);
            intent.putExtra("maban",maban);
            activityResultLauncher.launch(intent);

            // xóa bàn ăn
        } else if ( id == R.id.itXoa){
            boolean kt = foodTableDAO.XoaBanAN(maban);
            if (kt){
                ShowList();
                Toast.makeText(getActivity(), getResources().getString(R.string.successfuldelete), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), getResources().getString(R.string.deletefailed), Toast.LENGTH_SHORT).show();
            }

        }
        return super.onContextItemSelected(item);
    }
    //---------------------------

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem menuItem = menu.add(1,R.id.itThemBanAn,1,R.string.addTable);
        menuItem.setIcon(R.drawable.addtable);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    }
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()==1 && result != null){
                Intent intent = result.getData();
                intent.getStringExtra("tenbanthem");
               ShowList();
               ///-------------------------

            } if (result.getResultCode()== 99 && result != null){
                Intent intent = result.getData();
                boolean kt =  intent.getBooleanExtra("kiemtra",false);
                if (kt){
                    ShowList();
                    Toast.makeText(getActivity(), getResources().getString(R.string.successfulupdate), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.updatefailed), Toast.LENGTH_SHORT).show();
                }
            }
        }
    });

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itThemBanAn){
            Intent itenThemBanAn = new Intent(getActivity(), FoodTableActivity.class);
            activityResultLauncher.launch(itenThemBanAn);
        }
        return super.onOptionsItemSelected(item);
    }
    private void ShowList(){
        foodTableDTOList = foodTableDAO.danhAllBanAn();
        adapterShowTableFood = new AdapterShowTableFood(getActivity(),R.layout.custemfoodtable,foodTableDTOList);
        gridViewShowFoodTable.setAdapter(adapterShowTableFood);
        adapterShowTableFood.notifyDataSetChanged();

    }

}
