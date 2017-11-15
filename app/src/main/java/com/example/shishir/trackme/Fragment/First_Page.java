package com.example.shishir.trackme.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shishir.trackme.LocalDatabase;
import com.example.shishir.trackme.OnboardingActivity;
import com.example.shishir.trackme.R;

public class First_Page extends Fragment {

    private EditText contactOne, contactTwo;
    private static int flag = 0;
    private String c1, c2;
    private LocalDatabase localDatabase;
    private Button next;

    public First_Page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first__page, container, false);
        findViewById(view);
        return view;
    }

    private void findViewById(View view) {
        contactOne = (EditText) view.findViewById(R.id.contactOne);
        contactTwo = (EditText) view.findViewById(R.id.contactTwo);
        localDatabase = new LocalDatabase(getActivity());
        next = (Button) view.findViewById(R.id.nextAtContact);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1 = contactOne.getText().toString();
                c2 = contactTwo.getText().toString();
                if (c1.isEmpty()) {
                    toastMessage("Enter a contact number");
                    return;
                } else if (c1.length() < 11) {
                    toastMessage("Contact 1 is incorrect !");
                    return;
                } else {
                    if (!c2.isEmpty() && c2.length() < 11) {
                        toastMessage("Contact 2 is incorrect !");
                        return;
                    }
                    localDatabase.setContactOne(c1);
                    localDatabase.setContactTwo(c2);
                    ((OnboardingActivity) getActivity()).changePage(0);

                }
            }
        });
    }

    public void toastMessage(String m) {
        Toast.makeText(getActivity(), m, Toast.LENGTH_SHORT).show();
    }

}
