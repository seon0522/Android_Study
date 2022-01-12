package com.cookandroid.filedaiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    DatePicker dp;
    EditText ediDiary;
    Button btnWrite;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("간단 일기장");

        dp = (DatePicker)findViewById(R.id.datePicker1);
        ediDiary = (EditText)findViewById(R.id.edtDiary);
        btnWrite = (Button)findViewById(R.id.btnWrite);

        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                fileName = Integer.toString(year) + "_"
                        +Integer.toString(month + 1) + "_"
                        +Integer.toString(day) + ".txt";

                String str = readDiary(fileName);
                ediDiary.setText(str);
                btnWrite.setEnabled(true);
            }
        });

//        새로 저장했을 때
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName, Context.MODE_PRIVATE);

                    String str = ediDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + "이 저장됨", Toast.LENGTH_SHORT).show();

                }catch (IOException e){

                }
            }
        });

    }

    public String readDiary(String fileName){
        String dirayStr = null;

        FileInputStream inFs;

        try {
            inFs = openFileInput(fileName);
            byte txt[] = new byte[500];
            inFs.read(txt);
            inFs.close();

            dirayStr = (new String(txt)).trim();
            btnWrite.setText("수정 하기");

        }catch (IOException e){
            ediDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }

        return dirayStr;
    }
}