package org.techtown.today;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class day extends Fragment {

    MyAdapter adapter;
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

        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        getData();


        return rootView;

    }

    public void getset(List<Integer> check_list, List<String> tasks_list){
            this.check_list = check_list;
            this.tasks_list = tasks_list;

//            MainActivity activity = (MainActivity) getActivity();
//            activity.onChangeFragment(0);
    }



    // 리사이클뷰에 데이터 적재
    //List<Integer> check_list, List<String> tasks_list
    public void getData() {
        Log.d("recycle_day","-------------"+tasks_list.size()+"  /  "+check_list.size());


        for (int i = 0; i < check_list.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            data.setTask(tasks_list.get(i));

            if(check_list.get(i)==0){
                data.setCheck(false);
                Log.d("recycle_day", "-------------data----"+data.getTask().getClass().getName()+"  /  " +data.getCheck().getClass().getName());
            }
            else{
                data.setCheck(true);
                Log.d("recycle_day", "-------------data----"+data.getTask().getClass().getName()+"  /  " +data.getCheck().getClass().getName());
            }


            if (data == null) {
                Log.d("recycle_day", "-------------data null");
            }
            Log.d("recycle_day", "-------------" + tasks_list.get(i) + "  /  " + check_list.get(i));

            //각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
            Log.d("recycle_day", "-------------addItem: " + i);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }
}