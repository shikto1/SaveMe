package com.example.shishir.trackme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    LinearLayout contactL, intervalL,messageL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        findViewById();
    }

    private void findViewById() {
        contactL=(LinearLayout)findViewById(R.id.contactLayout);
        intervalL=(LinearLayout)findViewById(R.id.intervalLayout);
        messageL=(LinearLayout)findViewById(R.id.messageFormatLayout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
