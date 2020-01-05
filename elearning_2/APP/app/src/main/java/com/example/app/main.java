package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.courseAdapter;
import com.example.app.BOTTOMUI.account;
import com.example.app.BOTTOMUI.myclasses;
import com.example.app.BOTTOMUI.sourse;
import com.example.app.GETMESSAGE.GetRequest_Interface;
import com.example.app.GETMESSAGE.CourseList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class main extends AppCompatActivity {
    public List<CourseList> mcourseList;
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainui);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //注意这里是请求的主机名,必须以/结尾
                .baseUrl("http://10.0.2.2:8080/elearn/")
                .build();
        // 步骤5:创建 网络请求接口 的实例
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        //对 发送请求 进行封装
        Call<List<CourseList>> call = request.getCall();
        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<List<CourseList>>() {
            @Override
            public void onResponse(Call<List<CourseList>> call, Response<List<CourseList>> response) {
                mcourseList=response.body();
                //mcourseList.add(new CourseList("001","as",null,null,null,null,null,null,null,null,null,null,null,null,null,null));
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
                LinearLayoutManager layoutManager = new LinearLayoutManager(main.this);
                recyclerView.setLayoutManager(layoutManager);
                courseAdapter adapter = new courseAdapter(mcourseList);
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<CourseList>> call, Throwable t) {
                System.out.println(request.toString());
            }

            });

        Button myButton1=(Button)findViewById(R.id.button16);
        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main.this,main.class));
            }
        });
        Button myButton2=(Button)findViewById(R.id.button19);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main.this, myclasses.class));
            }
        });
        Button myButton3=(Button)findViewById(R.id.button18);
        myButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(main.this, sourse.class));
            }
        });
        Button myButton4=(Button)findViewById(R.id.button20);
        myButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(main.this, account.class));
            }
        });
    }
}
