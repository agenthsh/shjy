package com.example.hwang.testdb.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hwang.testdb.R;
import com.example.hwang.testdb.activity.InsertActivity;
import com.example.hwang.testdb.activity.MainActivity;
import com.example.hwang.testdb.db.DBManager;
import com.example.hwang.testdb.util.AppConstants;
import com.example.hwang.testdb.util.DateUtil;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class IncomeFragment extends Fragment implements View.OnClickListener{

    private TextView tvResult;
    private EditText etAmount;
    private EditText etMemo;
    private EditText etDate;
    private DBManager dbManager;




    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    public IncomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(AppConstants.TAG, "IncomeFragment onCreate");
        dbManager = new DBManager(getActivity().getApplicationContext(), "INCOME_DATA.db", null, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(AppConstants.TAG, "IncomeFragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        loadResource(view);
        etDate.setText(DateUtil.getDate());


        return view;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_insert:
                insertDB();
                break;
            case R.id.et_date:
                getDate();
                break;
        }
    }

    private void insertDB() {
        String amount = etAmount.getText().toString();
        String memo = etMemo.getText().toString();
        String date = etDate.getText().toString();

        try {
          //  dbManager.dropTable();
            dbManager.insert(memo, amount, date);
            ClearLog();
            WriteLog(dbManager.PrintData());
            startActivity(new Intent(getActivity(), MainActivity.class));
        } catch (Exception e) {
            WriteLog(e.getMessage());
            Log.d(AppConstants.TAG, e.getMessage());
        }
    }

    private void getDate() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                mDateSetListener,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                now.get(Calendar.DAY_OF_WEEK)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
        Log.d(AppConstants.TAG, "get date");

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePickerDialog view, int year, int month, int day, int dayOfWeek) {

            String strDate = DateUtil.convertDateForm(year, month, day, dayOfWeek);
            etDate.setText(strDate);
        }
    };

    public void WriteLog(String log) {
        if (TextUtils.isEmpty(log)) {
            return;
        }
        tvResult.setMovementMethod(new ScrollingMovementMethod());
        tvResult.append(" : " + DateUtil.getTime() + log + "\n");

        Log.d(AppConstants.TAG, log);
    }

    public void ClearLog() {
        tvResult.setText("");
    }

    private void loadResource(View view) {
        Button button = (Button) view.findViewById(R.id.btn_insert);
        button.setOnClickListener(this);

        tvResult = (TextView) view.findViewById(R.id.tv_result);
        etAmount = (EditText) view.findViewById(R.id.et_amount);
        etMemo = (EditText) view.findViewById(R.id.et_memo);
        etDate = (EditText) view.findViewById(R.id.et_date);
        etDate.setOnClickListener(this);
    }
}
