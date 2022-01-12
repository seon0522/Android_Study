package com.cookandroid.a10intentdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {

    TextView tvID,tvPassword;
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("데이터 전달 확인");

        tvID=findViewById(R.id.tvId);
        tvPassword=findViewById(R.id.tvPassword);

        btnReturn=findViewById(R.id.btnReturn);

        //★ 입력하기
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Integer password = intent.getIntExtra("password", 0);

        tvID.setText(id);
        tvPassword.setText(password.toString());


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}