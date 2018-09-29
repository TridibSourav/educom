package com.example.tspc.educom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent= getIntent();
        res=findViewById(R.id.resultTxt);

        String r= intent.getStringExtra("result");
        res.setText(r);
    }
}
