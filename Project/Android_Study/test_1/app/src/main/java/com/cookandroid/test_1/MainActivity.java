package com.cookandroid.test_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ro;
    int rotation;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        ro = (EditText)findViewById(R.id.edit);
        rotation = Integer.parseInt(ro.getText().toString());
        Log.d("TAG", "onCreate: " + rotation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// 메뉴를 등록하고
        MenuInflater minflater = getMenuInflater();
        minflater.inflate(R.menu.menu_ani, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//메뉴를 선택하기'
        Log.d("TAG", "onOptionsItemSelected: " + rotation);
        switch (item.getItemId()){
            case R.id.rRotation:
                imageView.setRotationX(rotation);
                break;
            case R.id.mDog:
                imageView.setImageResource(R.drawable.dog);
                break;
            case R.id.mCat:
                imageView.setImageResource(R.drawable.cat);
                break;
            case R.id.mRabbit:
                imageView.setImageResource(R.drawable.rabbit);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}