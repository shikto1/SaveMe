package com.example.shishir.trackme.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shishir.trackme.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class First_Page extends Fragment {


    public First_Page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first__page, container, false);
    }

}
