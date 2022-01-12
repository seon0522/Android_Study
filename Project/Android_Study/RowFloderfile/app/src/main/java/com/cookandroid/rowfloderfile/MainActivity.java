package com.cookandroid.rowfloderfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
//    각각의 라디오 버튼에 아이디 줘야함.
    RadioButton rdoThi, rdoSecond;
    Button btnNewA, btnSecondA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        rdoSecond = (RadioButton) findViewById(R.id.radioSecondA);
        rdoThi = (RadioButton)findViewById(R.id.radioThirdA);
        btnNewA = (Button)findViewById(R.id.btnNewActivity);

        btnNewA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if (rdoSecond.isChecked() == true){
                    intent = new Intent(getApplicationContext(), SecondActivity.class);
                    intent.putExtra("과목", "국어");
                    intent.putExtra("순서", 2);
                }else {
                    intent = new Intent(getApplicationContext(), ThirdActivity.class);
                    intent.putExtra("과목", "수학");
                    intent.putExtra("순서", 3);
                }
                startActivity(intent);

            }
        });


    }
}