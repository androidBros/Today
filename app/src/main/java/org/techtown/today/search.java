package org.techtown.today;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class search extends Fragment {
    SearchView searchView;
    TextView search_time;

    static MainActivity mactivity;

    List<Integer> search_date_list = new ArrayList<>();
    List<String> search_tasks_list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
        Log.d("search", "search-------------search 프래그먼트 들어옴---------------------");
        searchView = rootView.findViewById(R.id.searchview);
        search_time = rootView.findViewById(R.id.search_time);

        RecyclerView recyclerView = rootView.findViewById(R.id.search_recycle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        search_Adapter sa = new search_Adapter();
//        recyclerView.setAdapter(sa);

        DBHelper dbHelper = new DBHelper(getActivity());




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerView.setLayoutManager(linearLayoutManager);
                search_Adapter sa = new search_Adapter();
                recyclerView.setAdapter(sa);
                dbHelper.searchTask(query);
                search_date_list = dbHelper.search_date_list;
                search_tasks_list = dbHelper.search_tasks_list;
                Log.d("search", "search------------"+search_date_list+"/"+search_tasks_list+"------------------");

                for(int i=0;i<search_tasks_list.size(); i++){
                    Data data = new Data();
                    data.setDate(search_date_list.get(i));
                    data.setSearchTasks(search_tasks_list.get(i));

                    sa.addItem(data);
                    Log.d("search", "search------------additem 실행됨-----------------");
                }

                sa.notifyDataSetChanged();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return rootView;
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof Activity){
            mactivity = (MainActivity) getActivity();
            Log.d("day", "--------mactivity---------" + mactivity);
        }
    }
    public void go_selected_date(String date, String nojdate){
        mactivity.selected_date = nojdate;
        mactivity.edit_btn.setEnabled(true);
        mactivity.add_btn.setEnabled(true);
        mactivity.back_btn.setEnabled(true);
        mactivity.day_btn.setEnabled(true);
        mactivity.next_btn.setEnabled(true);
        mactivity.search_btn.setEnabled(true);
        mactivity.onChangeFragment(0);
        mactivity.day_btn.setText(date);
        Log.d("day", "--------mactivity---------" + mactivity.selected_date);
    }
}