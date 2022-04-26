package org.techtown.today;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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

    SQLiteDatabase database;
    String taskTable = "tasktable";



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


        //임시로
        today_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTask();
                Log.d("DB Create", "--------------------오늘 버튼 클릭 ----------------------");
            }
        });




        // DB 테이블 생성
//        database = openOrCreateDatabase(taskDB, MODE_PRIVATE, null);
//        createTable(taskDB);
        createDatabase();

    }

    //DB 생성
    private void createDatabase(){
        String taskDB = "task.db"; // DB이름
        database = openOrCreateDatabase(taskDB, MODE_PRIVATE, null);//DB가 존재하면 오픈. 존재하지않은면 생성
        Log.d("DB Create", "--------------------DB 생성----------------------");
        createTable();
    }


    private void createTable(){
        Log.d("DB Create", "--------------------DB Create----------------------");


        try {
            if (database == null) {
                Log.d("DB Create", "--------------------DB Create faild----------------------");
                return;
            }

            database.execSQL("CREATE TABLE if not exists " + taskTable + "("
                    + "_id integer PRIMARY KEY autoincrement,"
                    + "startdate date,"
                    + "enddate date,"
                    + "day string,"
                    + "exe boolean)");
            Log.d("DB Create", "--------------------DB 테이블 생성 ----------------------");
        }

        catch (Exception e){
            e.printStackTrace();
        }

    }
    //Date startdate, Date enddate, String day, Boolean exe
    public void insertTask(){
        Log.d("DB Create", "--------------------insertTask()----------------------");

        try {
            if(database ==null){
                Log.d("DB Create", "--------------------DB insert faild----------------------");
                return;
            }
            if(taskTable ==null){
                Log.d("DB Create", "--------------------DB insert faild----------------------");
                return;
            }

            database.execSQL("insert into " + taskTable
                    + "(startdate, enddate, day, exe)"
                    + "values"
                    +"(2022-04-26, 2022-04-26, 13, 1)");
            Log.d("DB Create", "--------------------DB insert 성공----------------------");

        }catch (Exception e){
            e.printStackTrace();
        }

//        database.execSQL("insert into" + taskTable
//                    + "(startdate, enddate, day, exe)"
//                    + "values"
//                    +"("+startdate+", "+enddate+", "+day+", "+exe+")"
//        );

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