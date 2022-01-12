package com.cookandroid.listviewsss;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //문자열 배열을 정의하고 항목을 사용하는 대신, ArryayList<String>형의 비어 있는 변수 선언함
        final ArrayList<String> midList = new ArrayList<String>();
        ListView list = (ListView) findViewById(R.id.listView1);

        //★ 입력하기, ArrayAdapter()형 변수 선언 및 midList를 생성자의 마지막 파라미터로 사용
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  midList);
        list.setAdapter(adapter);

        final EditText edtItem = (EditText) findViewById(R.id.edtItem);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);

        //★ 입력하기, btnAdd 클릭시 midList에 에디트텍스트 내용을 add() 메서드로 추가
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                midList.add(edtItem.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });


        //★ 입력하기, list 항목을 롱클릭하면 remove() 메서드로 midList 항목 삭제
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                midList.remove(i);''
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }

}
