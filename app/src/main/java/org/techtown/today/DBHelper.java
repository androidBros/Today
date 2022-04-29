package org.techtown.today;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    String sqlexe;

    public static String taskDB = "task.db";
    public static int version = 1;
    String taskTable = "tasktable"; // 테이블 이름

    day day = new day();

    public DBHelper(@Nullable Context context) {
        super(context, taskDB, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DB Create", "--------------------DB onCreate ----------------------");

        sqLiteDatabase.execSQL("CREATE TABLE if not exists " + taskTable + "("
                + "_id integer PRIMARY KEY autoincrement,"
                + "startdate int,"
                + "enddate int,"
                + "day int,"
                + "task String,"
                + "exe int)");
        Log.d("DB Create", "--------------------DB 테이블 생성 ----------------------");
    }



        // Data 저장 - String startdate, String enddate, String day, String tasks, int exe
    public void insertTask(int startdate, int enddate, int day, String tasks, int exe) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Log.d("DB Create", "--------------------DB insertTask()----------------------");

        try {
            if (taskTable == null) {
                Log.d("DB Create", "--------------------DB insert faild----------------------");
                return;
            }
//            sqlexe = "insert into taskTable"
//                    + "(startdate, enddate, day, task, exe)"
//                    + "values"
//                    + "(20220429, 20220429, 0, 'dfddfdfdf', 0)";
//
//            sqLiteDatabase.execSQL(sqlexe);

            sqlexe = "insert into taskTable"
                    + "(startdate, enddate, day, task, exe)"
                    + "values"
                    + "('" + startdate + "', '" + enddate + "', '" + day + "', '" + tasks + "', " + exe + ")";

            Log.d("DB Create", sqlexe);
            sqLiteDatabase.execSQL(sqlexe);

            Log.d("DB Create", "--------------------DB insert 성공----------------------");


        } catch (Exception e) {
            Log.d("DB Create", "--------------------DB Exception----------------------");
            e.printStackTrace();
        }
    }



    public void getTaskDB(String date){

        int Intdate = Integer.parseInt(date);

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<Integer> check_list = new ArrayList<>();
        List<String> tasks_list = new ArrayList<>();

        String sqlread = "SELECT * FROM taskTable WHERE startdate = "+Intdate+" AND "+"enddate = "+Intdate;
        Log.d("DB Create", "-----------------"+sqlread);


        Cursor cursor = sqLiteDatabase.rawQuery(sqlread,null);

        while (cursor.moveToNext()){
            Log.d("DB Create", "-----------------"+cursor.getString(4) +"  /  "+cursor.getInt(5));
            tasks_list.add(cursor.getString(4));
            check_list.add(cursor.getInt(5));

        }

        day.getset(check_list, tasks_list);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
