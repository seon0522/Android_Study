package com.cookandroid.food;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> restdata = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ArrayList<info> restlist = new ArrayList<info>();
    ListView listView;
    final int REST_INFO = 21;
    final int NEW_REST = 22;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        Log.d("추적", "시작");
        setTitle("나의 맛집");
        setListView();
    }


    public void onClick(View v)
    {
        Log.d("추적", "맛집 추가 클릭");
        Intent intent = new Intent(MainActivity.this, Menuinfo.class);
        intent.putExtra("restlist",restdata);

        startActivityForResult(intent, NEW_REST);
    }

    public void setListView()
    {
//        해당 리스트뷰 챙겨오기
        listView = (ListView)findViewById(R.id.listview);

        //어댑터 만듬
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,restdata);
        listView.setAdapter(adapter);

        //꾹 눌렀을때 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

                //정보를 삭제하는지 묻는 대화상자 나타남
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("삭제확인")
//                        .setIcon(R.drawable.potato)
                        .setMessage("정말 삭제하시겠습니까?")
                        .setNegativeButton("취소",null)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                //삭제 클릭시 아래꺼
                                restdata.remove(position);
                                restlist.remove(position);
                                adapter.notifyDataSetChanged();
                                tv.setText("맛집 리스트("+restdata.size()+"개)");
                                Snackbar.make(view,"삭제되었습니다.",2000).show();
                            }
                        })
                        .show();
                return true;
            }
        });

        //클릭시 상세정보가 나타남
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(MainActivity.this, Menu.class);
                info res = restlist.get(position);
                intent.putExtra("restinfo",res);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("추적", "4. 완료?");
        if(requestCode == NEW_REST)
        {
            if(resultCode == RESULT_OK)
            {
                info res = data.getParcelableExtra("newrest"); //새 rest 받아옴
                restdata.add(res.getName());
                restlist.add(res);
                adapter.notifyDataSetChanged();
                tv.setText("맛집 리스트("+restdata.size()+"개)");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}