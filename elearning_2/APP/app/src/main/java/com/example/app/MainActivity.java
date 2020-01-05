package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.app.BOTTOMUI.account;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText et_user_name,et_psw;//编辑框
    private Button b1;
    private TextView b2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_user_name=findViewById(R.id.et_user_name);
        et_psw=findViewById(R.id.et_psw);
        b1=findViewById(R.id.btn_login);
        b2=findViewById(R.id.tv_register);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = et_user_name.getText().toString();
                String strPassword = et_psw.getText().toString();

                //判断用户名和密码是否正确（为可以进行测试，将用户名和密码都定义为admin 123）
                if(strUsername.equals("admin") && strPassword.equals("123456")){
                    startActivity(new Intent(MainActivity.this,main.class));
                }else {
                    Toast.makeText(MainActivity.this,"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,signup.class));
            }
        });
    }
}



