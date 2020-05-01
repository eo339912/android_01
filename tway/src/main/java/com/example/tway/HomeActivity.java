package com.example.tway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    TextView nameText;
    TextView ageText;
    TextView ddayText;
    TextView genderblood;
    ImageView babyFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nameText = findViewById(R.id.nameText);
        ageText = findViewById(R.id.ageText);
        ddayText = findViewById(R.id.ddayText);
        genderblood = findViewById(R.id.genderblood);
        babyFace = findViewById(R.id.babyFace);

        //한건조회 -> 마지막으로 추가된 행을 조회한다.
        try {
            info_selectOne();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void info_selectOne() throws ParseException {
        ArrayList<HashMap<String, String>> list =  new InfoDAO().selectOne(getApplicationContext());
        String name = "";
        String birth = "";
        String gender = "";
        String blood = "";
        String face = "";
        for(HashMap<String, String> map : list){
            name += map.get("baby_name");
            birth += map.get("birthday"); //yyyy-mm-dd
            gender += map.get("gender");
            blood += map.get("abo");
            face += map.get("baby_face");
        }

        //calendar date
        //오늘 날짜
        Calendar today = Calendar.getInstance();
        int nYear = today.get(Calendar.YEAR);
        int nMonth = today.get(Calendar.MONTH)+1;
        int nDay = today.get(Calendar.DAY_OF_MONTH);

        //입력한 값의 년, 월, 일
        //String birth -> date -> String -> int
        SimpleDateFormat transBirth = new SimpleDateFormat("yyyy-M-d");
        Date dBirth = transBirth.parse(birth);

        //년
        SimpleDateFormat transYear = new SimpleDateFormat("yyyy");
        String sYear = transYear.format(dBirth);
        int aYear = Integer.parseInt(sYear);
        //월
        SimpleDateFormat transMonth = new SimpleDateFormat("M");
        String sMonth = transMonth.format(dBirth);
        int aMonth = Integer.parseInt(sMonth);
        //일
        SimpleDateFormat transDate = new SimpleDateFormat("dd");
        String sDate = transDate.format(dBirth);
        int aDay = Integer.parseInt(sDate);

        //나이 계산
        int age = nYear - aYear;
        //생일이 안지났으면 한살뺀다.
        if (aMonth * 100 + aDay > nMonth * 100 + nDay){
            age--;
        }

        String sage = Integer.toString(age);

        //d_day 계산
        Calendar d_day = Calendar.getInstance();

        d_day.set(aYear, aMonth, aDay);


        long dd_day = d_day.getTimeInMillis() / (24*60*60*1000);
        long tt_day = Calendar.getInstance().getTimeInMillis() / (24*60*60*1000);

        long result = tt_day - dd_day;
        String strCount = String.valueOf(result);

        System.out.println(birth);
        System.out.println(aYear + "-"+ aMonth + "-"+ aDay);
        System.out.println(nYear + "-"+ nMonth + "-"+ nDay);

        nameText.setText(name);
        ageText.setText("만 " + sage + "세");
        ddayText.setText("태어난지" + strCount + "일 째");
        genderblood.setText(gender + " / " + blood);

        Bitmap face_bite = StringToBitmap(face);
        babyFace.setImageBitmap(face_bite);
    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
