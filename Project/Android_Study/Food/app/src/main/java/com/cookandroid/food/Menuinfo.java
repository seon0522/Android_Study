package com.cookandroid.food;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

//메뉴 등록
public class Menuinfo extends AppCompatActivity {

        EditText etname,ettel,etmenu1,etmenu2,etmenu3, etmemo,etaddr ;
        info res;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menuinfo);
            etname = (EditText)findViewById(R.id.etname);
            ettel = (EditText)findViewById(R.id.ettel);
            etmenu1 = (EditText)findViewById(R.id.etmenu1);
            etmenu2 = (EditText)findViewById(R.id.etmenu2);
            etmenu3 = (EditText)findViewById(R.id.etmenu3);
            etmemo = (EditText)findViewById(R.id.etmemo);
            etaddr = (EditText)findViewById(R.id.etaddr);
            setTitle("기록");

        }


        public void onClicks(View v)
        {
            if (v.getId() == R.id.btnCancel)
            {
                finish();
            }
            else
            {
                setcategorynum();
                res.setMenu(etmenu1.getText().toString());
                res.setMenu(etmenu2.getText().toString());
                res.setMenu(etmenu3.getText().toString());
                res.setMenu(etmemo.getText().toString());
                res.setDate(finddate());

                Log.d("날짜", "" + res);

                Intent intent = getIntent();

                intent.putExtra("newrest", res);  //Parcelable한 Restaurant를 첨부
                Log.d("추적", "3. 취소 외에 버튼을 늚");
                setResult(RESULT_OK,intent);
                finish();
            }
        }



        public void setcategorynum()
        {
            res = new info(etname.getText().toString(),
                    ettel.getText().toString(),
                    etaddr.getText().toString());
        }

        public String finddate()
        {
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String fmdate = sdf.format(date);
            return fmdate;
        }

    }