package com.cookandroid.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//리스트에서 메뉴보기
public class Menu extends AppCompatActivity {

    TextView name, menu1,menu2,menu3, memo ,tvtel,tvaddress,tvdate;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("정보 상세보기");
        name = (TextView)findViewById(R.id.txtname);
        menu1 = (TextView)findViewById(R.id.etmenu1);
        menu2 = (TextView)findViewById(R.id.etmenu2);
        menu3 = (TextView)findViewById(R.id.etmenu3);
        memo = (TextView)findViewById(R.id.etmemo);
        tvtel = (TextView)findViewById(R.id.tvTel);
        tvaddress = (TextView)findViewById(R.id.tvURL);
        tvdate = (TextView)findViewById(R.id.tvRegdate);
        back = (Button)findViewById(R.id.btnback) ;

        Intent intent = getIntent();
        info res = intent.getParcelableExtra("restinfo");

        name.setText(res.getName());
        menu1.setText(res.getmenu1());
        menu2.setText(res.getmenu2());
        menu3.setText(res.getmenu3());
        memo.setText(res.getmemo());
        tvtel.setText(res.getTel());
        tvdate.setText(res.getDate());
        tvaddress.setText(res.getHomepage());
    }

    public void onClick(View v)
    {
        Intent intent = getIntent();
        info res = intent.getParcelableExtra("restinfo");
        switch (v.getId())
        {
            case R.id.btnback:
                finish();
                break;
            case R.id.imageView2://전화
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:/"+res.getTel()));
                startActivity(intent2);
                break;

            case R.id.imageView3:
                Intent intent3 = new Intent(Intent.ACTION_VIEW,Uri.parse(res.getHomepage()));
                startActivity(intent3);
                break;
        }
    }
}