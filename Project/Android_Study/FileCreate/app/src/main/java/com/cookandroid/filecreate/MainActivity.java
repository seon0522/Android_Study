package com.cookandroid.filecreate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button btnWrite, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWrite = (Button)findViewById(R.id.fileWrite);
        btnRead = (Button)findViewById(R.id.fileRead);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                바이트형태로 읽고, 저장하고 String으로 사용

                try {
//                    저장
                    FileOutputStream outFs = openFileOutput("file.txt",
                            Context.MODE_PRIVATE);
                    String str = "효선";
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(MainActivity.this,"file.text 가 생성됨", Toast.LENGTH_SHORT).show();
                }catch (IOException e){

                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream inFs = openFileInput("file.txt");
                    byte txt[] = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);
                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    inFs.close();

                }catch (IOException e){
                    Toast.makeText(MainActivity.this, "파일없음", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}