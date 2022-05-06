package org.techtown.today;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class day extends Fragment {


    //MyAdapter adapter = new MyAdapter();
    List<Integer> id_list = new ArrayList<>();
    List<Integer> check_list = new ArrayList<>();
    List<String> tasks_list = new ArrayList<>();
    static Activity mactivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("day","----------------day onCreateView 실행---------------");
        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_day, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.task_recycle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);


        DBHelper dbHelper = new DBHelper(getActivity());
        Log.d("day","-------------getActivity---------"+getActivity());
        MainActivity activity = (MainActivity) getActivity();


        if (activity.selected_date == null){
            activity.selected_date = activity.nojDate;
        }




        dbHelper.getTaskDB(activity.selected_date);
        this.id_list = dbHelper.id_list;
        this.check_list = dbHelper.check_list;
        this.tasks_list = dbHelper.tasks_list;

        for (int i=0 ; i<check_list.size(); i++){
            Data data = new Data();
            data.setTask(tasks_list.get(i));
            data.setID(id_list.get(i));

            if(check_list.get(i)==0){
                data.setCheck(false);
            }else{
                data.setCheck(true);
            }

            adapter.addItem(data);
        }
        adapter.notifyDataSetChanged();


        return rootView;

    }


    public void update(int check,int id){
        Log.d("day", "--------update 안에서 mactivity -------------" + mactivity);
        if (mactivity!= null) {
            DBHelper dbHelper = new DBHelper(mactivity);
            Log.d("day", "------getactivity 객체---------------" + mactivity);
            Log.d("day", "--------check    id  -------------" + check + id);
            dbHelper.updateTable(check, id);
        }
        else{

            Log.d("day","------getactivity 객체---------------"+mactivity);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof Activity){
            mactivity = (Activity)context;
            Log.d("day", "--------mactivity---------" + mactivity);
        }
    }
}