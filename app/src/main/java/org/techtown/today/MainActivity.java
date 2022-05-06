package org.techtown.today;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Button today_btn;
    Button add_btn;
    Button back_btn;
    Button day_btn;
    Button next_btn;
    Button edit_btn;
    Button delete_btn;

    day day = new day(); // 홈 화면 프레그먼트; //그 날 일정 뜰 프래그먼트
    add_schedule add_schedule; // 추가 눌렀을 때 프래그먼트
    add_short add_short; // 당일 일정 추가 화면
    add_long add_long; // 장기 일정 추가 화면


    long mNow;
    public Date mDate;
    public String nojDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    Calendar myCalendar = Calendar.getInstance();
    String selected_date;


    DatePickerDialog.OnDateSetListener myDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateLabel();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //day = new day(); // 홈 화면 프레그먼트
        onChangeFragment(0);

        add_schedule = new add_schedule(); // 추가 버튼 눌렀을 때
        add_short = new add_short();
        add_long = new add_long();

        today_btn = findViewById(R.id.today_btn);
        add_btn = findViewById(R.id.add_btn);
        back_btn = findViewById(R.id.back_btn);
        day_btn = findViewById(R.id.day_btn);
        next_btn = findViewById(R.id.next_btn);
        edit_btn = findViewById(R.id.edit_btn);
        delete_btn = findViewById(R.id.delete_btn);



        Log.d("MainActivity", "DBHelper--------------------DB call----------------------");

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChangeFragment(1);
            }
        });


        // 캘린더 띄우기
        day_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, myDatePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "DELETE 상태--------"+delete_btn.getVisibility());
                if (delete_btn.getVisibility() == View.INVISIBLE) {
                    delete_btn.setVisibility(View.VISIBLE);
                    edit_btn.setText("편집취소");
                }else{
                    delete_btn.setVisibility(View.INVISIBLE);
                    edit_btn.setText("편집");
                }
            }
        });

        getTime();
        Log.d("MainActivity", "--------------------"+nojDate+"----------------------");

    }


    private void updateLabel() {
        String myFormat = "yyyy-MM-dd";    // 출력형식   2021/07/26
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
      //  nojDate = myFormat.replace("-","");
        day_btn.setText(sdf.format(myCalendar.getTime()));
        selected_date = sdf.format(myCalendar.getTime()).replace("-","");
        Log.d("MainActivity", "seleceted_date--------------------"+selected_date+"----------------------");


        fragmentDetach(day);
        fragmentAttach(day);
    }


    public void fragmentDetach(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.detach(fragment).commit();
    }


    public void fragmentAttach(Fragment fragment){
        Log.d("MainActivity", "seleceted_date--------------------selectDate() 실행됨----------------------");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.attach(fragment).commit();

    }


    // 현재 시간 얻기
    private void getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        Log.d("MainActivity", "mData--------------------"+mDate+"----------------------");

        day_btn.setText(mFormat.format(mDate));


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        nojDate = dateFormat.format(mDate);
        nojDate = nojDate.replace("-","");

    }

    public void onBackPressed(){
        if(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) == day){
            finish();
        }else{
            super.onBackPressed();
        }
    }


    public void onChangeFragment(int index){
        if (index == 0){ // 홈으로 오게 당일 일정 뜨는 거
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, day).addToBackStack(null).commit();
        }else if (index == 1){ // 추가 버튼 눌렀을 때
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, add_schedule).addToBackStack(null).commit();
        }else if (index == 2){ // 당일 일정 추가 화면
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, add_short).addToBackStack(null).commit();
        }else if (index == 3){ // 장기 일정 추가 화면
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, add_long).addToBackStack(null).commit();
        }
    }




}