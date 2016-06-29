package com.example.hwang.testdb.activity;


import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hwang.testdb.db.DBManager;
import com.example.hwang.testdb.R;
import com.example.hwang.testdb.activity.uihelper.BackPressCloseHandler;
import com.example.hwang.testdb.fragments.DatePickerFragment;
import com.example.hwang.testdb.fragments.OneFragment;
import com.example.hwang.testdb.fragments.ThreeFragment;
import com.example.hwang.testdb.fragments.TwoFragment;

import com.example.hwang.testdb.util.AppConstants;
import com.wdullaer.materialdatetimepicker.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView tvResult;
    FloatingActionButton fab;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(AppConstants.TAG, "MainActivity onCreate");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setOnClickListener(this);

        backPressCloseHandler = new BackPressCloseHandler(this);

        final DBManager dbManager = new DBManager(getApplicationContext(), "INCOME_DATA.db", null, 1);

        // DB에 저장 될 속성을 입력받는다
        final EditText etName = (EditText) findViewById(R.id.et_name);


        // final EditText etAmount = (EditText) findViewById(R.id.et_amount);

 //       Button dateButton = (Button)findViewById(R.id.date_button);



//        dateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar now = Calendar.getInstance();
//                DatePickerDialog dpd = DatePickerDialog.newInstance(
//                        MainActivity.this,
//                        now.get(Calendar.YEAR),
//                        now.get(Calendar.MONTH),
//                        now.get(Calendar.DAY_OF_MONTH)
//                );
//                dpd.setThemeDark(modeDarkDate.isChecked());
//                dpd.vibrate(vibrateDate.isChecked());
//                dpd.dismissOnPause(dismissDate.isChecked());
//                dpd.showYearPickerFirst(showYearFirst.isChecked());
//                if (modeCustomAccentDate.isChecked()) {
//                    dpd.setAccentColor(Color.parseColor("#9C27B0"));
//                }
//                if (titleDate.isChecked()) {
//                    dpd.setTitle("DatePicker Title");
//                }
//                if (limitDates.isChecked()) {
//                    Calendar[] dates = new Calendar[13];
//                    for(int i = -6; i <= 6; i++) {
//                        Calendar date = Calendar.getInstance();
//                        date.add(Calendar.MONTH, i);
//                        dates[i+6] = date;
//                    }
//                    dpd.setSelectableDays(dates);
//                }
//                if (highlightDates.isChecked()) {
//                    Calendar[] dates = new Calendar[13];
//                    for(int i = -6; i <= 6; i++) {
//                        Calendar date = Calendar.getInstance();
//                        date.add(Calendar.WEEK_OF_YEAR, i);
//                        dates[i+6] = date;
//                    }
//                    dpd.setHighlightedDays(dates);
//                }
//                dpd.show(getFragmentManager(), "Datepickerdialog");
//            }
//        });


    }





    public void WriteLog(String log)
    {
        tvResult.setMovementMethod(new ScrollingMovementMethod());
        tvResult.append(log + "\n");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "일별");
        adapter.addFragment(new TwoFragment(), "주별");
        adapter.addFragment(new ThreeFragment(), "월별");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void onButtonClicked(View v){
//        DialogFragment newFragment = new DatePickerFragment();
//        newFragment.show(getFragmentManager(),"Date Picker");
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                startActivity(new Intent(MainActivity.this, InsertActivity.class));

//                Calendar now = Calendar.getInstance();
//                DatePickerDialog dpd = DatePickerDialog.newInstance(
//                        MainActivity.this,
//                        now.get(Calendar.YEAR),
//                        now.get(Calendar.MONTH),
//                        now.get(Calendar.DAY_OF_MONTH)
//                );
//
//                dpd.show(getFragmentManager(), "Datepickerdialog");


                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }











}
