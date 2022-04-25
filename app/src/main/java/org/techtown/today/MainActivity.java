package org.techtown.today;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button today_btn;
    Button add_btn;
    Button back_btn;
    Button day_btn;
    Button next_btn;

    day day; //그 날 일정 뜰 프래그먼트
    add_schedule add_schedule; // 추가 눌렀을 때 프래그먼트
    add_short add_short; // 당일 일정 추가 화면
    add_long add_long; // 장기 일정 추가 화면

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        day = new day(); // 홈 화면 프레그먼트
        onChangeFragment(0);

        add_schedule = new add_schedule(); // 추가 버튼 눌렀을 때
        add_short = new add_short();
        add_long = new add_long();

        today_btn = findViewById(R.id.today_btn);
        add_btn = findViewById(R.id.add_btn);
        back_btn = findViewById(R.id.back_btn);
        day_btn = findViewById(R.id.day_btn);
        next_btn = findViewById(R.id.next_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChangeFragment(1);
            }
        });
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