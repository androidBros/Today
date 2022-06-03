package org.techtown.today;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class search_Adapter extends RecyclerView.Adapter<search_Adapter.ItemViewHolder> {
    ArrayList<Data> search_listData = new ArrayList<>();



    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_view, parent, false);


        return new ItemViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position){

        holder.onBind(search_listData.get(position));


    }

    @Override
    public int getItemCount() {
        return search_listData.size();
    }


    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        Log.d("MyAdapter","addItem 호출됨-------------------------------------"+data.getDate()+" / "+data.getSearchTasks());
        search_listData.add(data);
        Log.d("MyAdapter","addItem 호출됨-----------------add 실행됨--------------------");

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {


        private TextView searchDate;
        private TextView searchTasks;
        search search = new search();

        public ItemViewHolder(View itemView) {
            super(itemView);
            searchDate = itemView.findViewById(R.id.search_time);
            searchTasks = itemView.findViewById(R.id.search_task);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String date = (String) searchDate.getText();
                    String nojdate = date.replaceAll("-","");
                    //int search_selected_date = Integer.parseInt(date);
                    search.go_selected_date(date, nojdate);
                }
            });
        }
        void onBind(Data data){
            Log.d("MyAdapter","onBind-------------------------------------"+data.getDate()+" / "+data.getSearchTasks());
            StringBuffer sb = new StringBuffer();
            String date = Integer.toString(data.getDate());
            sb.append(date);
            sb.insert(4,'-');
            sb.insert(7,'-');
            searchDate.setText(sb);
            searchTasks.setText(data.getSearchTasks());
        }
    }




}
