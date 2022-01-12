package com.cookandroid.food;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class info implements Parcelable {
    private String name;
    private String tel;
    private ArrayList<String> menu;
    private String homepage;
    private String date;

    public info(String name, String tel, String homepage)

    {
        this.name = name;
        this.tel = tel;
        this.homepage = homepage;
        menu = new ArrayList<String>();
    }

// 데이터를 넘기기 위해 필요
    public static final Creator<info> CREATOR = new Creator<info>() {
        @Override
        public info createFromParcel(Parcel in) {
            return new info(in);
        }

        @Override
        public info[] newArray(int size) {
            return new info[size];
        }
    };

    //직렬화를 푸는 로직
    protected info(Parcel in) {
        name = in.readString();
        tel = in.readString();
        menu = in.createStringArrayList();
        homepage = in.readString();
        date = in.readString();
    }

//    Parcelable 적용
    @Override
    public int describeContents() {
        return 0;
    }

//    데이터 직렬화 / dest에 순차적으로 데이터 저장
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(tel);
        dest.writeStringList(menu);
        dest.writeString(homepage);
        dest.writeString(date);
    }

//    이름, 전화번호, 메뉴1,메뉴2, 메뉴3, 홈페이지, 날짜 얻기
    public String getName() {
        return name;
    }
    public String getTel() {
        return tel;
    }
    public String getmenu1()
    {
        return menu.get(0);
    }
    public String getmenu2()
    {
        return menu.get(1);
    }
    public String getmenu3()
    {
        return menu.get(2);
    }
    public String getmemo()
    {
        return menu.get(3);
    }

    public String getHomepage() {
        return homepage;
    }
    public String getDate() { return date; }

    //    이름, 전화번호, 메뉴1,메뉴2, 메뉴3, 홈페이지, 날짜 설정
    public void setName(String name) {
        this.name = name;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setMenu(String item) {
        menu.add(item);
    } // 메뉴 값 넣기
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }
    public void setDate(String date) {
        this.date = date;
    }


}
