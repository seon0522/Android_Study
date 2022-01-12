package com.cookandroid.vote;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle("투표 결과");

        //★ 입력하기 예제 10-13 서브 액티비티(ResultActivity.java)의 코드 1
        //앞 화면에서 보낸 투표 결과 값을 받는다.
        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("imgName");


        // 9개의 TextView, RatingBar 객체배열
        TextView tv[] = new TextView[imageName.length];
        RatingBar rbar[] = new RatingBar[imageName.length];

        // 9개의 TextView, RatingBar ID 배열
        Integer tvID[] = { R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5,
                R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9 };
        Integer rbarID[] = { R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4,
                R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9 };

        // TextView, RatingBar 개체 찾기.
        for (int i = 0; i < voteResult.length; i++) {
            tv[i] = (TextView) findViewById(tvID[i]);
            rbar[i] = (RatingBar) findViewById(rbarID[i]);
        }

        // ★ 입력하기 각 TextVeiw 및 RatingBar에 넘겨 받은 값을 반영.
        for (int i = 0; i < voteResult.length; i++){
            tv[i].setText(imageName[i]);
            rbar[i].setRating((float)voteResult[i]);
        }

        Integer imageFileld[] = {
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
                R.drawable.pic4,R.drawable.pic5, R.drawable.pic6,
                R.drawable.pic7, R.drawable.pic8,R.drawable.pic9
        };

        TextView tvTop = (TextView)findViewById(R.id.tvTop);
        ImageView ivTop = (ImageView)findViewById(R.id.ivTop);

        int maxEntry = 0;

        for (int i = 0; i < voteResult.length; i++){
            if (voteResult[maxEntry] < voteResult[i])
                maxEntry  = i;
        }

        tvTop.setText(imageName[maxEntry]);
        ivTop.setImageResource(imageFileld[maxEntry]);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();

            }
        });
    }
}
