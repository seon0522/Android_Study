package com.cookandroid.singersqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;   // myDBHelper 클래스 변수
    EditText edtName, edtNumber, edtNameResult, edtNumberResult;
    Button btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB;   //SQLiteDatabase클래스 변수

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        edtName = (EditText) findViewById(R.id.edtName);
        edtNumber = (EditText) findViewById(R.id.edtNumber);

//        화면에 출력할 것
        edtNameResult = (EditText) findViewById(R.id.edtNameResult);
        edtNumberResult = (EditText) findViewById(R.id.edtNumberResult);

        btnInit = (Button) findViewById(R.id.btnInit);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnSelect = (Button) findViewById(R.id.btnSelect);

        myHelper = new myDBHelper(this);    // myDBHelper 객체 생성. myDBHelper() 생성자실행되어 groupDB 파일 생성됨

        // <초기화> 클릭시 동작 리스너
        btnInit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });

        //<입력> 클릭시 동작 리스터
        btnInsert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // execSQL() 메서드는 결과를 돌려받지 않는 쿼리 수행시
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES('"
                        + edtName.getText().toString()+ "', "
                        + edtNumber.getText().toString() + ");");
                Log.d("s",edtName.getText().toString() );
                sqlDB.close();

                Toast.makeText(getApplicationContext(), "입력됨",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //<조회> 클릭시 동작 리스너
        btnSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // cursor 변수에는 테이블에 입력된 모든 행 데이터가 들어 있는 상태. 현재 첫 번째 행을 가리키고 있음
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL", null);


                String strNames = "그룹이름" + "\r\n" + "--------" + "\r\n";
                String strNumbers = "인원" + "\r\n" + "--------" + "\r\n";

                while (cursor.moveToNext()){
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }

                // 누적한 문자열을 에디트텍스트에 출력
                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                //cursor와 데이터베이스를 닫는다.
                cursor.close();
                sqlDB.close();
            }
        });

    }



//    DB생성
    // SQLiteOpenHelper 클래스 상속 받아 myDBHelper  클래스 정의
    public class myDBHelper extends SQLiteOpenHelper {


//    groupDB라는 db파일이 생성됨.
        public myDBHelper(Context context){
            super(context, "groupDB", null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE groupTBL ( gName CHAR(20) PRIMARY KEY, gNumber INTEGER );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(sqLiteDatabase);
        }
    }
}
