package org.techtown.today;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    String sqlexe;

    public static String taskDB = "task.db";
    public static int version = 1;
    String taskTable = "tasktable"; // 테이블 이름

    public DBHelper(@Nullable Context context) {
        super(context, taskDB, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DB Create", "--------------------DB onCreate ----------------------");

        sqLiteDatabase.execSQL("CREATE TABLE if not exists " + taskTable + "("
                + "_id integer PRIMARY KEY autoincrement,"
                + "startdate String,"
                + "enddate String,"
                + "day String,"
                + "task String,"
                + "exe int)");
        Log.d("DB Create", "--------------------DB 테이블 생성 ----------------------");
    }



        // Data 저장 - String startdate, String enddate, String day, String tasks, int exe
    public void insertTask(String startdate, String enddate, String day, String tasks, int exe) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        Log.d("DB Create", "--------------------DB insertTask()----------------------");

        try {
//            if (database == null) {
//                Log.d("DB Create", "--------------------DB insert faild----------------------");
//                return;
//            }
            if (taskTable == null) {
                Log.d("DB Create", "--------------------DB insert faild----------------------");
                return;
            }


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


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
