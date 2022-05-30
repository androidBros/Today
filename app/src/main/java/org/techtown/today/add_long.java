package org.techtown.today;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class add_long extends Fragment {
    Button startdate_btn;
    Button enddate_btn;
    Calendar myCalendar;

    Button sun_btn;
    Button mon_btn;
    Button tue_btn;
    Button wed_btn;
    Button thu_btn;
    Button fri_btn;
    Button sat_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_add_long, container, false);

        startdate_btn = rootView.findViewById(R.id.startdate_btn);
        enddate_btn = rootView.findViewById(R.id.enddate_btn);
        sun_btn = rootView.findViewById(R.id.sun_btn);
        mon_btn = rootView.findViewById(R.id.mon_btn);
        tue_btn = rootView.findViewById(R.id.tue_btn);
        wed_btn = rootView.findViewById(R.id.wed_btn);
        thu_btn = rootView.findViewById(R.id.thu_btn);
        fri_btn = rootView.findViewById(R.id.fri_btn);
        sat_btn = rootView.findViewById(R.id.sat_btn);


        myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener startDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel("start");
            }
        };
        DatePickerDialog.OnDateSetListener endDatePicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel("end");
            }
        };


        startdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), startDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
        });

        enddate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getContext(), endDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
             }
        });


        sun_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sun_btn.isSelected() == true){
                    sun_btn.setSelected(false);
                }
                else{
                    sun_btn.setSelected(true);
                }

            }
        });

        mon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mon_btn.isSelected() == true){
                    mon_btn.setSelected(false);
                }
                else{
                    mon_btn.setSelected(true);
                }

            }
        });

        tue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tue_btn.isSelected() == true){
                    tue_btn.setSelected(false);
                }
                else{
                    tue_btn.setSelected(true);
                }

            }
        });

        wed_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wed_btn.isSelected() == true){
                    wed_btn.setSelected(false);
                }
                else{
                    wed_btn.setSelected(true);
                }
            }
        });

        thu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(thu_btn.isSelected() == true){
                    thu_btn.setSelected(false);
                }
                else{
                    thu_btn.setSelected(true);
                }
            }
        });

        fri_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fri_btn.isSelected() == true){
                    fri_btn.setSelected(false);
                }
                else{
                    fri_btn.setSelected(true);
                }
            }
        });

        sat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sat_btn.isSelected() == true){
                    sat_btn.setSelected(false);
                }
                else{
                    sat_btn.setSelected(true);
                }
            }
        });


        return rootView;
    }
    private void updateLabel(String str) {
        String myFormat = "yyyy-MM-dd";    // 출력형식   2021/07/26
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        //  nojDate = myFormat.replace("-","");
        if (str == "start") {
            startdate_btn.setText(sdf.format(myCalendar.getTime()));
        }else{
            enddate_btn.setText(sdf.format(myCalendar.getTime()));
        }
    }
}