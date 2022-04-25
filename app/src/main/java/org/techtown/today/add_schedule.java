package org.techtown.today;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class add_schedule extends Fragment {

    Button add_short_btn;
    Button add_long_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView= (ViewGroup) inflater.inflate(R.layout.fragment_add_schedule, container, false);

        MainActivity activity = (MainActivity) getActivity();
        add_short_btn = rootView.findViewById(R.id.add_short_btn);
        add_long_btn = rootView.findViewById(R.id.add_long_btn);

        add_short_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onChangeFragment(2);
            }
        });

        add_long_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onChangeFragment(3);
            }
        });


        return rootView;
    }
}