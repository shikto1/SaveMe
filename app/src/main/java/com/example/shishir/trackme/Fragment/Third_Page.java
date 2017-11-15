package com.example.shishir.trackme.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shishir.trackme.LocalDatabase;
import com.example.shishir.trackme.OnboardingActivity;
import com.example.shishir.trackme.R;

public class Third_Page extends Fragment {


    private EditText smsFormatEt;
    private LocalDatabase localDatabase;
    private Button next;
    public Third_Page() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third__page, container, false);
        findViewById(view);
        return view;
    }

    private void findViewById(View view) {
        smsFormatEt = (EditText) view.findViewById(R.id.smsFormat);
        localDatabase = new LocalDatabase(getActivity());
        next=(Button)view.findViewById(R.id.nextAtSmsFormat);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String smsFormat = smsFormatEt.getText().toString();
                if (smsFormat.isEmpty()) {
                    Toast.makeText(getActivity(), "Write your sms format", Toast.LENGTH_SHORT).show();
                }
                else {
                    localDatabase.setMessageFormat(smsFormat);
                    ((OnboardingActivity) getActivity()).changePage(2);
                }
            }
        });
    }


}
