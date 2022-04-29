package org.techtown.today;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class day extends Fragment {

    MyAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_day, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.task_recycle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        getData();

        return rootView;

    }



    // 리사이클뷰에 데이터 적재
    public void getData() {
        List<String> listTask = Arrays.asList("국화", "사막", "수국", "해파리", "코알라");
        List<Boolean> booleanList = Arrays.asList(true,true,false,true,false);

        for (int i = 0; i < listTask.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTask(listTask.get(i));
            data.setCheck(booleanList.get(i));


            Log.d("recycle_day","dddddddddddddddddd"+listTask.size()+"ddddddddddddddddddddddd");
            Log.d("recycle_day","dddddddddddddddddd"+i+"ddddddddddddddddddddddd");

            //각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}