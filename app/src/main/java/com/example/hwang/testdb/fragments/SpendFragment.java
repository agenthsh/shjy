package com.example.hwang.testdb.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hwang.testdb.R;

public class SpendFragment extends Fragment implements View.OnClickListener {

    TextView tvResult;

    public SpendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_income, container, false);

//        Button button = (Button) view.findViewById(R.id.btn_select);
//        button.setOnClickListener(this);
//        tvResult = (TextView) view.findViewById(R.id.tv_result);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

       //     case R.id.et_date:
      //          Toast.makeText(getActivity(), "One Fragment", Toast.LENGTH_SHORT)
     //                   .show();
     //           WriteLog("test");
              //    break;


        }

    }

    public void WriteLog(String log)
    {
        tvResult.setMovementMethod(new ScrollingMovementMethod());
        tvResult.append(log + "\n");
    }

    public void ClearLog()
    {
        tvResult.setText("");
    }



}
