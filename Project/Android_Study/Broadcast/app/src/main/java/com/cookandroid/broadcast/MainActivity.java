package com.cookandroid.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView ivBattery;
    EditText edtBattery;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("배터리 상태 체크");

        ivBattery = (ImageView) findViewById(R.id.ivBattery);
        edtBattery = (EditText) findViewById(R.id.edtBattery);

    }

    //onPause() 메서드 : 등록된 BR을 해제함
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br);
    }

    // onResume() : 인텐트 필터를 생성하고, ACTION_BATTERY_CHANGED 액션 추가한 후
    // BR에 등록

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br, iFilter);
    }

    // BroadcastReceiver 객체 생성
    BroadcastReceiver br = new BroadcastReceiver() {

        // 배터리가 변하면 onReceive() 메서드가 자동 호출됨
        //onReceive() 메서드는 인텐트의 액션이 배터리가 변경된 신호인
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                edtBattery.setText("현재 충전량 : " + remain + "\n");

                if (remain >= 90)
                    ivBattery.setImageResource(R.drawable.battery_100);
                else if (remain >= 70)
                    ivBattery.setImageResource(R.drawable.battery_80);
                else if (remain >= 50)
                    ivBattery.setImageResource(R.drawable.battery_60);
                else if (remain >= 10)
                    ivBattery.setImageResource(R.drawable.battery_20);
                else
                    ivBattery.setImageResource(R.drawable.battery_0);

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                switch (plug) {
                    case 0:
                        edtBattery.append("전원 연결 : 안됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        edtBattery.append("전원 연결 : 어댑터 연결됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        edtBattery.append("전원 연결 : USB 연결됨");
                        break;
                }
            }
        }
    };
}
