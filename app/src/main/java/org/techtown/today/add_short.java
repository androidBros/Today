package org.techtown.today;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;

public class add_short extends Fragment {

    Button insert_btn;
    Button cancel_btn;
    EditText insertTask;

    String tasks;
    String date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_add_short, container, false);

        insert_btn = rootView.findViewById(R.id.insert_btn);
        cancel_btn = rootView.findViewById(R.id.cancel_btn);
        insertTask = rootView.findViewById(R.id.insertTask);

        tasks = insertTask.getText().toString();

        DBHelper dbHelper = new DBHelper(getActivity());


        MainActivity activity = (MainActivity) getActivity();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = transFormat.format(activity.mDate);
        date = date.replace("-","");



        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tasks = insertTask.getText().toString();
                Log.d("add_short",tasks + " / "+ date);

                if(tasks.isEmpty()){
                    Toast.makeText(getActivity(),"할일을 작성해 주세요",Toast.LENGTH_LONG).show();
                }
                else{

                    //String startdate, String enddate, String day, String tasks, int exe
                    dbHelper.insertTask(date,date,null,tasks,0);
                    Toast.makeText(getActivity(),"추가되었습니다. ",Toast.LENGTH_LONG).show();

                    insertTask.setText("");

                }


            }
        });


        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTask.setText("");
                activity.onChangeFragment(1);
            }
        });



        return rootView;
    }
}