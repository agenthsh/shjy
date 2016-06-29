package com.example.hwang.testdb.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.text.style.WrapTogetherSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hwang.testdb.R;
import com.example.hwang.testdb.activity.MainActivity;
import com.example.hwang.testdb.db.DBManager;
import com.example.hwang.testdb.util.AppConstants;
import com.example.hwang.testdb.util.DateUtil;

public class OneFragment extends Fragment implements View.OnClickListener {

    TextView tvResult;
    private DBManager dbManager;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DBManager(getActivity().getApplicationContext(), "INCOME_DATA.db", null, 1);
        Log.d(AppConstants.TAG, "One Fragment onCreate");
    }

    private void getIncomeData() {
        WriteLog(dbManager.PrintData());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

//        Button button = (Button) view.findViewById(R.id.btn_select);
//        button.setOnClickListener(this);
//        tvResult = (TextView) view.findViewById(R.id.tv_result);

        loadResource(view);
        getIncomeData();
        return view;
    }

    public void WriteLog(String log) {
        if (TextUtils.isEmpty(log)) {
            return;
        }
        tvResult.setMovementMethod(new ScrollingMovementMethod());
        tvResult.append(" : " + DateUtil.getTime() + log + "\n");

        Log.d(AppConstants.TAG, log);
    }

    public void ClearLog()
    {
        tvResult.setText("");
    }

    private void loadResource(View view) {
        Button button = (Button) view.findViewById(R.id.btn_delete);
        button.setOnClickListener(this);

        tvResult = (TextView) view.findViewById(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_delete:
                dbManager.deleteAll();
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }


    }

}
