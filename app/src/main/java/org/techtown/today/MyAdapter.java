package org.techtown.today;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {
// adapter에 들어갈 list 입니다.

    ArrayList<Data> listData = new ArrayList<>();
    int checked_id;


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_view, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
        checked_id = listData.get(position).getID();
        day day = new day();
        Log.d("MyAdapter","ID------------------onbindviewholder 들어옴-------------------");
        holder.task_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("MyAdapter","ID-----------------checklistener 들어옴--------------------");
                if(holder.task_checkbox.isChecked()==true){
                    day.update(1,checked_id);

                }
                else{
                    day.update(0,checked_id);

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        Log.d("MyAdapter","RecyclerView의 총 개수 입니다.-------------------"+listData.size()+"-------------------");
        return listData.size();
    }

    void addItem(Data data) {
        // 외부에서 item을 추가시킬 함수입니다.
        Log.d("MyAdapter","addItem 호출됨-------------------------------------"+data.getTask()+" / "+data.getCheck());
        listData.add(data);

    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private CheckBox task_checkbox;
        private TextView task_item;
        private int id_;


        ItemViewHolder(View itemView) {
            super(itemView);
            task_checkbox = itemView.findViewById(R.id.task_checkBox);
            task_item = itemView.findViewById(R.id.task_item);
        }

        void onBind(Data data) {
            task_checkbox.setChecked(data.getCheck());
            task_item.setText(data.getTask());
            id_ = data.getID();
            Log.d("MyAdapter","ID-------------------------------------"+data.getID());
        }



    }



}
