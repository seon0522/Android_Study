package com.cookandroid.project13_1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listViewMP3;
    Button btnPlay, btnStop, btnPause, btnPauStart;
    TextView tvMP3;
    ProgressBar pbMP3;

    String selectedMP3;
    Integer selectedSong;

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] mp3List = {"song1","song2","song3"};
        final Integer songId[] = {R.raw.song1,R.raw.song2,R.raw.song3};

        listViewMP3 = (ListView)findViewById(R.id.listViewMP3);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, mp3List);
        listViewMP3.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewMP3.setAdapter(adapter);
//        기본 선택
        listViewMP3.setItemChecked(0, true);

        listViewMP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMP3 = mp3List[i];
                selectedSong = songId[i];
            }
        });

//       선택없이 듣기할 경우의 default 설정
       selectedMP3 = mp3List[0];
        selectedSong = songId[0];

        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnPause = (Button)findViewById(R.id.btnPause);
        btnPauStart = (Button)findViewById(R.id.btnPauStart);

        tvMP3 = (TextView)findViewById(R.id.tvMP3);
        pbMP3 = (ProgressBar)findViewById(R.id.pbMP3);

        btnPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mPlayer = MediaPlayer.create(MainActivity.this, selectedSong);
                mPlayer.start();
                btnPlay.setClickable(false);
                btnStop.setClickable(true);
                tvMP3.setText("실행중인 음악 : " + selectedMP3);
                pbMP3.setVisibility(view.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                mPlayer.reset();
                btnPlay.setClickable(true);
                btnStop.setClickable(false);
                tvMP3.setText("실행중인 음악 : ");
                pbMP3.setVisibility(View.INVISIBLE);
            }
        });

//        버튼 stop 기본 설정
        btnStop.setClickable(false);

        btnPause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                btnPause.setVisibility(View.GONE);
                btnPauStart.setVisibility(View.VISIBLE);
                mPlayer.pause();
            }
        });





    }
}