package com.cookandroid.twowayintent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

@SuppressWarnings("deprecation")
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second 액티비티");

        //★ 입력하기, 메인액티비티로부터 받은 두 값을 더한다
        Intent intent = getIntent();

        String calc = (intent.getStringExtra("Calc"));

        int calValue = 0;
        if (calc.equals("+")){
            calValue = intent.getIntExtra("num1", 0)+
                    intent.getIntExtra("num2", 0);
        }else if (calc.equals("-")){
            calValue = intent.getIntExtra("num1", 0)-
                    intent.getIntExtra("num2", 0);
        }else if (calc.equals("*")){
            calValue = intent.getIntExtra("num1", 0)*
                    intent.getIntExtra("num2", 0);
        }else {
            calValue = intent.getIntExtra("num1", 0)/
                    intent.getIntExtra("num2", 0);
        }

        final int retValue = calValue;


        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.e("intent", "  =======  ======= " + retValue);
                //★ 입력하기, 인텐트를 생성하고 더한 값을 넣어 , setResult()로 메인 액티비티로 돌려준다.
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("retValue", retValue);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });
    }
}