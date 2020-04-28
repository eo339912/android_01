package com.example.edumng;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtuserid = (TextView) findViewById(R.id.txtUserid);
        TextView txtuserpw = (TextView) findViewById(R.id.txtUserpw);
        TextView txtusername = (TextView) findViewById(R.id.txtUsername);
        Button btnManage = (Button) findViewById(R.id.btnmanage);

        Intent intent = getIntent();

        String id = intent.getExtras().getString("id");
        txtuserid.setText(id);

        String pw = intent.getExtras().getString("pw");
        txtuserpw.setText(pw);

        String name = intent.getExtras().getString("name");
        txtusername.setText(name);

        //관리자가 아니년 회원관리 버튼이 안보이게 처리
        if(! id.equals("admin")){
            btnManage.setVisibility(View.GONE);
        }

    }
}
