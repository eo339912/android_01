package com.example.mymemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MemoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memo);

        Button b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText editTextTitle = (EditText) findViewById(R.id.editTitle);
                String title =  editTextTitle.getText().toString();
                EditText editTextContent = (EditText) findViewById(R.id.editContent);
                String Content =  editTextContent.getText().toString();

                MemoVO vo = new MemoVO();
                vo.setTitle(title);
                vo.setContent(Content);
                new MemoDAO().insert(getApplicationContext(), vo);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

                finish();
            }
        });

        //리스트클릭시 리스트 정보 받아오기
        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        EditText editTextTitle = (EditText) findViewById(R.id.editTitle);

        editTextTitle.setText(name);
    }


}
