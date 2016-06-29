package com.example.hwang.testdb.activity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hwang.testdb.db.DBManager;
import com.example.hwang.testdb.R;
import com.example.hwang.testdb.fragments.DatePickerFragment;
import com.example.hwang.testdb.fragments.IncomeFragment;
import com.example.hwang.testdb.fragments.SpendFragment;
import com.example.hwang.testdb.fragments.ThreeFragment;
import com.example.hwang.testdb.util.AppConstants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class InsertActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(AppConstants.TAG, "InsertActivity onCreate");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        final DBManager dbManager = new DBManager(getApplicationContext(), "INCOME_DATA.db", null, 1);

        // DB에 저장 될 속성을 입력받는다
        final EditText etName = (EditText) findViewById(R.id.et_name);
        //final EditText etAmount = (EditText) findViewById(R.id.et_amount);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        final EditText etDate = (EditText) findViewById(R.id.et_date);
//        etDate.setOnClickListener(this);

    }



    public void WriteLog(String log)
    {
        tvResult.setMovementMethod(new ScrollingMovementMethod());
        tvResult.append(log + "\n");
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new IncomeFragment(), "수입");
        adapter.addFragment(new SpendFragment(), "지출");
        adapter.addFragment(new ThreeFragment(), "이체");
        viewPager.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.hide();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.editText:
                //startActivity(new Intent(MainActivity.this, InsertActivity.class));

//                Calendar now = Calendar.getInstance();
//                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
//                        InsertActivity.this,
//                        now.get(Calendar.YEAR),
//                        now.get(Calendar.MONTH),
//                        now.get(Calendar.DAY_OF_MONTH)
//                );
//
//                DatePickerFragment dpf = new DatePickerFragment().newInstance();
//                dpf.show(getFragmentManager().beginTransaction(), "DatePickerFragment");
//
//                dpd.show(getFragmentManager(), "Datepickerdialog");


                break;
        }
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
