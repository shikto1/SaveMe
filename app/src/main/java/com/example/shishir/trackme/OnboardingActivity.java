package com.example.shishir.trackme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.shishir.trackme.Fragment.First_Page;
import com.example.shishir.trackme.Fragment.Second_Page;
import com.example.shishir.trackme.Fragment.Third_Page;

public class OnboardingActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private Button next;
    private LocalDatabase localDatabase;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        next = (Button) findViewById(R.id.next);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        viewPager.setAdapter(adapter);

        localDatabase = new LocalDatabase(this);
        radioGroup.getChildAt(0).setEnabled(true);
        radioGroup.check(R.id.one);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentITem = viewPager.getCurrentItem();
                if (currentITem == 2) {
                    finishOnBoarding();
                } else {
                    if (currentITem == 1) {
                        radioGroup.getChildAt(1).setEnabled(false);
                        radioGroup.getChildAt(2).setEnabled(true);
                        radioGroup.check(R.id.three);
                        next.setText("Done");
                    }
                    if (currentITem == 0) {
                        radioGroup.getChildAt(0).setEnabled(false);
                        radioGroup.getChildAt(1).setEnabled(true);
                        radioGroup.check(R.id.two);
                    }
                    viewPager.setCurrentItem(currentITem + 1, true);
                }
            }
        });

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

    }

    private void finishOnBoarding() {
        localDatabase.setOnboarding(true);
        Intent mainA = new Intent(this, MainActivity.class);
        startActivity(mainA);
        finish();
    }

    FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new First_Page();
                case 1:
                    return new Second_Page();
                case 2:
                    return new Third_Page();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };
}
