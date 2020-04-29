package com.example.tway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button insertBtn;
    EditText editTName;
    EditText editTbirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertBtn = findViewById(R.id.insertBtn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTName = findViewById(R.id.nameText) ;
                String name = editTName.getText().toString() ;

                editTbirth = findViewById(R.id.birthText) ;
                String birth = editTbirth.getText().toString() ;

                InfoVO vo = new InfoVO();
                vo.setBaby_name(name);
                vo.setBirthday(birth);

                //성별
                final RadioGroup gender = findViewById(R.id.gender);
                int gid = gender.getCheckedRadioButtonId();
                RadioButton gd = findViewById(gid);
                vo.setGender(gd.getText().toString());
                String gender1 = gd.getText().toString();

                //혈액형
                final RadioGroup blood = findViewById(R.id.blood);
                int bid = blood.getCheckedRadioButtonId();
                RadioButton bd = findViewById(bid);
                vo.setAbo(bd.getText().toString());
                String abo = bd.getText().toString();

                new InfoDAO().insert(getApplicationContext(), vo);

            }
        });
    }

}
