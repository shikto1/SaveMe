package com.example.shishir.trackme;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button startStopButton;
    private  ImageButton settingBtn;


    private LocationManager locationManager;
    private static boolean gpsIsEnabled = false;
    private static boolean receiverIsRegistered = false;
    private TextView serviceTv;
    private LocalDatabase localDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localDatabase = new LocalDatabase(this);
        if (!localDatabase.OnBoardingIsVisited()) {
            startActivity(new Intent(this, OnboardingActivity.class));
            finish();
        }
        setContentView(R.layout.activity_main);
        findViewById();
    }


    private void findViewById() {
        startStopButton = (Button) findViewById(R.id.startStopButton);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        settingBtn = (ImageButton) findViewById(R.id.settingBtn);
        serviceTv=(TextView)findViewById(R.id.service);
        settingBtn.setOnClickListener(this);

        if (localDatabase.serviceIsRunning()) {
            startStopButton.setBackgroundResource(R.drawable.circle_button_stop);
            startStopButton.setText("STOP");
            serviceTv.setVisibility(View.VISIBLE);
        }

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            gpsIsEnabled = true;
        }

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().equals("START")) {
                    startStopButton.setText("STOP");
                    startStopButton.setBackgroundResource(R.drawable.circle_button_stop);
                    startMyService();
                    if (!gpsIsEnabled) {
                        showSettingsAlert();
                    }

                } else {
                    startStopButton.setText("START");
                    startStopButton.setBackgroundResource(R.drawable.circle_button_start);
                    stopMyService();
                }

            }
        });
    }


    private void stopMyService() {
        stopService(new Intent(this, MyService.class));
        localDatabase.setServiceRunning(false);
        serviceTv.setVisibility(View.INVISIBLE);
    }


    private void startMyService() {
        startService(new Intent(this, MyService.class));
        localDatabase.setServiceRunning(true);
        serviceTv.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("GPS settings");
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        Dialog dialog = alertDialog.create();
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        dialog.show();
    }


    @Override
    protected void onStart() {
        if (!receiverIsRegistered) {
            registerReceiver(GpsTracker, new IntentFilter("android.location.PROVIDERS_CHANGED"));
            receiverIsRegistered = true;
        }

        Toast.makeText(this, localDatabase.getContactOne() + "\n" + localDatabase.getInterval() + "\n" + localDatabase.getSmsFormat(),
                Toast.LENGTH_SHORT).show();
        super.onStart();
    }


    //Broadcast Receiver for checking weather GPS is turned On or Off..............................................................

    BroadcastReceiver GpsTracker = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                gpsIsEnabled = true;
            } else {
                gpsIsEnabled = false;
            }

        }
    };


    @Override
    protected void onPause() {
        if (receiverIsRegistered) {
            unregisterReceiver(GpsTracker);
            receiverIsRegistered = false;
        }
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ContactActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}
