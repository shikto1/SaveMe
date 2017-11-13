package com.example.shishir.trackme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Intent intent = getIntent();
        if (intent != null) {
            String from = intent.getStringExtra("from");
            if (from.equals("contact")) {
                ((TextView)findViewById(R.id.test)).setText("Contact");
            } else if (from.equals("interval")) {
                ((TextView)findViewById(R.id.test)).setText("Interval");
            } else if (from.equals("sms")) {
                ((TextView)findViewById(R.id.test)).setText("SMS");
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
