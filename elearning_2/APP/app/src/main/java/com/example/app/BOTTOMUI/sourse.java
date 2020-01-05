package com.example.app.BOTTOMUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.app.GETMESSAGE.GetRequest_Interface;
import com.example.app.GETMESSAGE.CourseList;
import com.example.app.R;
import com.example.app.main;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sourse extends AppCompatActivity {
    private List<Class> classlist=new ArrayList<>();
    private EditText medit;
    private Button mbutton;
    private Button mbutton2;
    private Button mbutton3;
    private TextView mtextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sourseui);
        medit = findViewById(R.id.editText3);
        mbutton=findViewById(R.id.buttonget);
        mbutton2=findViewById(R.id.buttontea);
        mbutton3=findViewById(R.id.buttonmeg);
        mtextview=findViewById(R.id.textView12);
        mbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        //注意这里是请求的主机名,必须以/结尾
                        .baseUrl("http://10.0.2.2:8080/elearn/")
                        .build();
                // 步骤5:创建 网络请求接口 的实例
                final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
                //对 发送请求 进行封装
                Call<CourseList> call = request.getCall1(medit.getText().toString());
                //步骤6:发送网络请求(异步)
                call.enqueue(new Callback<CourseList>() {
                    @Override
                    public void onResponse(Call<CourseList> call, Response<CourseList> response) {
                        CourseList result=response.body();
                        mtextview.setText(result.courseInfo(result));
                    }
                    @Override
                    public void onFailure(Call<CourseList> call, Throwable t) {
                        System.out.println(t.toString());
                    }});
            }
        });

        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sourse.this,teacher.class));
            }
        });
        mbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sourse.this,materials.class));
            }
        });

    }

}
