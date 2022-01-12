package com.cookandroid.vote;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("명화 선호도 투표");

        final int voteCount[] = new int[9];
        for (int i = 0; i < 9; i++)
            voteCount[i] = 0;

        // 9개의 이미지 버튼 객체배열
        ImageView image[] = new ImageView[9];

        // 9개의 이미지버튼 ID 배열
        Integer imageId[] = { R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5,
                R.id.iv6, R.id.iv7, R.id.iv8, R.id.iv9 };

        final String imgName[] = { "독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
                "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들",
                "해변에서" };

        //★ 입력하기 예제10-11 메인 액티비티(MainActivity.java)의 코드 2 입력
        for (int i = 0; i < imageId.length; i++){
            final int index;
            index = i;
            image[index] = (ImageView)findViewById(imageId[index]);
            image[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(), imgName[index]+" : 총 "+ voteCount[index] + "표",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }


        //★ 입력하기  예제10-12 메인 액티비티(MainActivity.java)의 코드 3 입력
        Button btnResult = (Button)findViewById(R.id.btnResult);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("imgName", imgName);

                startActivity(intent);
            }
        });
    }
}
