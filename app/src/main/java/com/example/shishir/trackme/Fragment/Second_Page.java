package com.example.shishir.trackme.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.shishir.trackme.LocalDatabase;
import com.example.shishir.trackme.OnboardingActivity;
import com.example.shishir.trackme.R;

public class Second_Page extends Fragment implements View.OnClickListener {

    private Button fiveMinBtn, tenMinBtn, twentyMinBtn, thirtyMinBtn;
    private LocalDatabase localDatabase;
    private int flagInterval = 0;
    private Button next;
    public Second_Page() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second__page, container, false);
        findViewById(view);
        return view;
    }

    private void findViewById(View view) {
        fiveMinBtn = (Button) view.findViewById(R.id.fiveMin);
        tenMinBtn = (Button) view.findViewById(R.id.tenMin);
        twentyMinBtn = (Button) view.findViewById(R.id.twentyMin);
        thirtyMinBtn = (Button) view.findViewById(R.id.thirtyMin);
        localDatabase = new LocalDatabase(getActivity());
        next=(Button)view.findViewById(R.id.nextAtInterval);

        fiveMinBtn.setOnClickListener(this);
        tenMinBtn.setOnClickListener(this);
        twentyMinBtn.setOnClickListener(this);
        thirtyMinBtn.setOnClickListener(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagInterval == 0) {
                    Toast.makeText(getActivity(), "Select an Interval", Toast.LENGTH_SHORT).show();
                } else {
                    ((OnboardingActivity) getActivity()).changePage(1);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fiveMin: {
                localDatabase.setInterVal("5");
                flagInterval = 5;
                fiveMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                tenMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                twentyMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                thirtyMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                break;
            }
            case R.id.tenMin: {
                flagInterval = 10;
                localDatabase.setInterVal("10");
                fiveMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                tenMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                twentyMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                thirtyMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                break;
            }
            case R.id.twentyMin: {
                flagInterval = 20;
                localDatabase.setInterVal("20");
                fiveMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                tenMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                twentyMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                thirtyMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                break;
            }
            case R.id.thirtyMin: {
                flagInterval = 30;
                localDatabase.setInterVal("30");
                fiveMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                tenMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                twentyMinBtn.setBackgroundResource(R.drawable.back_of_interval);
                thirtyMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                break;
            }
        }
    }

    private void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
