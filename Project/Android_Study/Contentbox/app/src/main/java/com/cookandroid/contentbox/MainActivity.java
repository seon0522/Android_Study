package com.cookandroid.contentbox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvEmail;
    Button button;
    EditText digEdtName,digEdtEmail;
//    View라는 하나의 위젯으로 생성
    View dialogView,toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("사용자 정보");

        tvName = (TextView)findViewById(R.id.user);
        tvEmail = (TextView)findViewById(R.id.userEmail);
        button = (Button)findViewById(R.id.mainBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                안의 위젯을 인플레이트 시켜서
                dialogView = (View) new View.inflate(MainActivity.this ,R.layout.dialog1, null);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
//                대화 상자에 위젯을 붙임
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", null);
                dlg.setPositiveButton("취소", null);
                dlg.show();
            }
        });

    }
}










//        final String versionArr[] = new String[]{"파이", "Q", "R"};
//        final Button btn = (Button)findViewById(R.id.button);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                getApplectioncontent() VS MainActivity.this
//                AlertDialog.Builder aleDig = new AlertDialog.Builder(MainActivity.this);
//                aleDig.setTitle("제목입니다.");
//                aleDig.setMessage("여기는 내용입니다.");
//                aleDig.setIcon(R.mipmap.ic_launcher);
////                목록생성
//                aleDig.setItems(versionArr, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        btn.setText(versionArr[i]);
//                    }
//                });
////                하나만 선택하는 것
//                aleDig.setSingleChoiceItems(versionArr, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        btn.setText(versionArr[i]);
//                    }
//                });
//                aleDig.show();
//            }
//        });