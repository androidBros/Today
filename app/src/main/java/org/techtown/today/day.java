package org.techtown.today;

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


    MyAdapter adapter = new MyAdapter();
    List<Integer> check_list = new ArrayList<>();
    List<String> tasks_list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("recycle_day","--------day onCreateView 실행~~~~~~~~~~~~~~~~!!!!!!!!!!!!!!!-----");
        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_day, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.task_recycle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        recyclerView.setAdapter(adapter);


        DBHelper dbHelper = new DBHelper(getActivity());
        MainActivity activity = (MainActivity) getActivity();
        Log.d("제발 들어와라","--------day onCreateView 실행~~~~~~-"+activity.nojDate);
        dbHelper.getTaskDB(activity.nojDate);
        this.check_list = dbHelper.check_list;
        this.tasks_list = dbHelper.tasks_list;
        Log.d("제발 들어와라","--------day onCreateView 실행~~~~~~-"+check_list.size());

        for (int i=0 ; i<check_list.size(); i++){
            Data data = new Data();
            data.setTask(tasks_list.get(i));

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




}