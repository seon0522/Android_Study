package com.cookandroid.content;

import android.Manifest;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    Button btnCall;
    EditText edtCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //마시멜로(Android6.0, API 23)부터는 직접 퍼미션을 허용하는 코드가 필요
//        매니페스트도 해 줘야 함.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, MODE_PRIVATE);

        btnCall = (Button) findViewById(R.id.btnCall);
        edtCall = (EditText) findViewById(R.id.edtCall);


        btnCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edtCall.setText(getCallHistory());
            }
        });
    }

    //통화 기록 내용을 검색하고 통화 기록을 문자열로 반환하는 함수
    public String getCallHistory() {

        //통화 기록 중에서 통화 날짜, 발신 또는 착신 여부, 전화번호, 통화 시간에 대한 문자열 배열 준비
        String[] callSet = new String[]{CallLog.Calls.DATE,
                CallLog.Calls.TYPE, CallLog.Calls.NUMBER,
                CallLog.Calls.DURATION};

          /* 통화 기록에 대해 callSet에서 설정한 내용을 조회함
            getContentResolver() 메서드는 ContentResolver를 반환하며,
            ContentResolver는 query(), insert(), delete(), update() 메서드를 사용하여
             CP의 데이터에 접근하고 변경할 수 있다 */
        Cursor c = getContentResolver().query(CallLog.Calls.CONTENT_URI,
                callSet, null, null, null);

        // 통화 기록이 한 건도 없다면 종료
        if (c == null)
            return "통화기록 없음";

        // 통화 기록의 문자열을 저장할 StringBuffer 변수 준비
        StringBuffer callBuff = new StringBuffer(); // 최대 100 통화 저장

        // 출력할 제목 준비
        callBuff.append("\n날짜 : 구분 : 전화번호 : 통화시간\n\n");

        c.moveToFirst();


        do {
            // 첫 필드(인덱스 0번)을 가져와서 날짜 형식으로 버퍼에 저장
            long callDate = c.getLong(0);
            SimpleDateFormat datePattern = new SimpleDateFormat("yyyy-MM-dd");
            String date_str = datePattern.format(new Date(callDate));
            callBuff.append(date_str + ":");

            //1번 필드의 데이터(발신/착신) 문자열을 버퍼에 저장
            if (c.getInt(1) == CallLog.Calls.INCOMING_TYPE)
                callBuff.append("착신 :");
            else
                callBuff.append("발신 :");

            //2번 필드의 내용(전화번호)을 버퍼에 저장
            callBuff.append(c.getString(2) + ":");

            //3번 필드의 내용(통화시간)을 버퍼에 저장
            callBuff.append(c.getString(3) + "초\n");
        } while (c.moveToNext());

        c.close();

        //저장한 모든 정보를 반환
        return callBuff.toString();
    }
}
