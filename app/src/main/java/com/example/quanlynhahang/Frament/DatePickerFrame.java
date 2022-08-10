package com.example.quanlynhahang.Frament;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.quanlynhahang.R;

import java.util.Calendar;

public class DatePickerFrame extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int nam = calendar.get(Calendar.YEAR);
        int thang = calendar.get(Calendar.MONTH);
        int ngay = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),this,ngay,thang,nam);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        EditText edNgaySinh = getActivity().findViewById(R.id.edNgaySinh);
        String SNgaySinh = i2+"/"+i1+"/"+i;
        edNgaySinh.setText(SNgaySinh);

    }
}
