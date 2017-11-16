package com.example.shishir.trackme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText contactOne, contactTwo;
    private LocalDatabase localDatabase;
    private Button fiveMinBtn, tenMinBtn, twentyMinBtn, thirtyMinBtn;
    private EditText smsFormatEt;
    private String contactOneStr, contactTwoStr, intervalStr, smsFormatStr;
    private Button saveChanger;
    private static int flagInterval = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        findViewById();
    }

    private void findViewById() {
        contactOne = (EditText) findViewById(R.id.contactOne);
        contactTwo = (EditText) findViewById(R.id.contactTwo);
        fiveMinBtn = (Button) findViewById(R.id.fiveMin);
        tenMinBtn = (Button) findViewById(R.id.tenMin);
        twentyMinBtn = (Button) findViewById(R.id.twentyMin);
        thirtyMinBtn = (Button) findViewById(R.id.thirtyMin);
        smsFormatEt = (EditText) findViewById(R.id.messageFormat);
        saveChanger = (Button) findViewById(R.id.saveChanges);
        localDatabase = new LocalDatabase(this);
        getSupportActionBar().setTitle("Setting");


        fiveMinBtn.setOnClickListener(this);
        tenMinBtn.setOnClickListener(this);
        twentyMinBtn.setOnClickListener(this);
        thirtyMinBtn.setOnClickListener(this);


        contactOneStr = localDatabase.getContactOne();
        contactTwoStr = localDatabase.getContactTwo();
        intervalStr = localDatabase.getInterval();
        smsFormatStr = localDatabase.getSmsFormat();


        contactOne.setText(contactOneStr);
        contactOne.setSelection(contactOneStr.length() );

        contactTwo.setText(contactTwoStr);
        contactTwo.setSelection(contactTwoStr.length() );

        smsFormatEt.setText(smsFormatStr);
        smsFormatEt.setSelection(smsFormatStr.length() );

        selectTimeInterval(intervalStr);
        flagInterval= Integer.parseInt(intervalStr);
    }

    private void selectTimeInterval(String intervalStr) {
        int interval = Integer.parseInt(intervalStr);
        switch (interval) {
            case 5: {
                fiveMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                break;
            }
            case 10: {
                tenMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                break;
            }
            case 20: {
                twentyMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                break;
            }
            case 30: {
                thirtyMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fiveMin: {
                flagInterval = 5;
                fiveMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                tenMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                twentyMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                thirtyMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                break;
            }
            case R.id.tenMin: {
                flagInterval = 10;
                fiveMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                tenMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                twentyMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                thirtyMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                break;
            }
            case R.id.twentyMin: {
                flagInterval = 20;
                fiveMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                tenMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                twentyMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                thirtyMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                break;
            }
            case R.id.thirtyMin: {
                flagInterval = 30;
                fiveMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                tenMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                twentyMinBtn.setBackgroundResource(R.drawable.circle_button_start);
                thirtyMinBtn.setBackgroundResource(R.drawable.circle_button_stop);
                break;
            }
        }
    }

    @Override
    protected void onStart() {
        saveChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conStr = contactOne.getText().toString();
                String conTwoStr = contactTwo.getText().toString();
                String intervalStr = String.valueOf(flagInterval);
                String smsFormat = smsFormatEt.getText().toString();

                if (conStr.isEmpty()) {
                    toastMessage("You must need at least one contact");
                } else if (conStr.length() < 11) {
                    toastMessage("Contact 1 is incorrect !");
                } else if (smsFormat.isEmpty()) {
                    toastMessage("Sms Format can not be empty !");
                } else {


                    localDatabase.setContactOne(conStr);
                    localDatabase.setContactTwo(conTwoStr);
                    localDatabase.setInterVal(intervalStr);
                    localDatabase.setMessageFormat(smsFormat);

                    Toast.makeText(ContactActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
        super.onStart();
    }

    private void toastMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
