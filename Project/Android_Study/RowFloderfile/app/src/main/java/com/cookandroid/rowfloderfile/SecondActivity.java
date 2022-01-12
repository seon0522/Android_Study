package com.cookandroid.rowfloderfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        TextView sub = (TextView)findViewById(R.id.subject);
        TextView num = (TextView)findViewById(R.id.number);

        Button btnReturn = (Button)findViewById(R.id.btnReturn);

        Intent intent = getIntent();
        String Subject = intent.getStringExtra("과목");
        int number = intent.getIntExtra("순서", 0);

        sub.setText(Subject);


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });



    }
}