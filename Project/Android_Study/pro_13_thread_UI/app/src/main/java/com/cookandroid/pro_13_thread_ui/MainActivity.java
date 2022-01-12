package com.cookandroid.pro_13_thread_ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    Button btn;
    ProgressBar pb1, pb2;
    TextView tv1, tv2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = (ProgressBar) findViewById(R.id.pb1);
        pb2 = (ProgressBar) findViewById(R.id.pb2);
        btn = (Button) findViewById(R.id.button1);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new Thread() {
                    public void run() {
                        for (int i = pb1.getProgress(); i < 100; i = i + 2) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    예외적으로 쓰레드 안에서 변경시킬 수 있다.그러나 UI쓰레드 안에 넣어주는게 좋겠다.
                                    pb1.setProgress(pb1.getProgress() + 2);
                                    tv1.setText("1번 진행률 : " + pb1.getProgress() + "%");
                                }
                            });


                            SystemClock.sleep(100);
                        }
                    }
                }.start();

                new Thread() {
                    public void run() {
                        for (int i = pb2.getProgress(); i < 100; i++) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
//                                    예외적으로 쓰레드 안에서 변경시킬 수 있다.그러나 UI쓰레드 안에 넣어주는게 좋겠다.
                                    pb2.setProgress(pb2.getProgress() + 1);
                                    tv2.setText("2번 진행률 : " + pb2.getProgress() + "%");

                                }
                            });


                            SystemClock.sleep(100);
                        }
                    }
                }.start();

            }
        });

    }
}
