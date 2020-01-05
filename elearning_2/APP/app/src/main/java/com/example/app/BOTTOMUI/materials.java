package com.example.app.BOTTOMUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.GETMESSAGE.CourseList;
import com.example.app.GETMESSAGE.GetRequest_Interface;
import com.example.app.GETMESSAGE.MaterialsList;
import com.example.app.GETMESSAGE.TeacherList;
import com.example.app.R;
import com.example.app.main;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class materials extends AppCompatActivity {
    private EditText medit;
    private Button mbuttonmeg;
    private Button mbutton1;
    private Button mbutton2;
    private Button mbutton3;
    private Button mbutton4;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private TextView t6;
    private TextView t7;
    private TextView t8;
    private TextView t9;
    private TextView t10;
    private TextView t11;
    private TextView t12;
    public List<MaterialsList> materialsLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materialsui);
        medit = findViewById(R.id.editTextkh);
        mbuttonmeg = findViewById(R.id.buttonmeg2);
        mbutton1=findViewById(R.id.button3);
        mbutton2=findViewById(R.id.button311);
        mbutton3=findViewById(R.id.button32);
        mbutton4=findViewById(R.id.button31);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView20);
        t3=findViewById(R.id.textView3);
        t4=findViewById(R.id.textView11);
        t5=findViewById(R.id.textView211);
        t6=findViewById(R.id.textView311);
        t7=findViewById(R.id.textView2);
        t8=findViewById(R.id.textView22);
        t9=findViewById(R.id.textView32);
        t10=findViewById(R.id.textView1);
        t11=findViewById(R.id.textView21);
        t12=findViewById(R.id.textView31);
        mbuttonmeg.setOnClickListener(new View.OnClickListener() {
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
                Call<List<MaterialsList>> call = request.getMeg(medit.getText().toString());
                //步骤6:发送网络请求(异步)
                call.enqueue(new Callback<List<MaterialsList>>() {
                    @Override
                    public void onResponse(Call<List<MaterialsList>> call, Response<List<MaterialsList>> response) {
                        materialsLists=response.body();
                        MaterialsList m1=materialsLists.get(0);
                        MaterialsList m2=materialsLists.get(1);
                        MaterialsList m3=materialsLists.get(2);
                        MaterialsList m4=materialsLists.get(3);
                        t1.setText("资料号:"+m1.getId());
                        t2.setText("课程号:"+m1.getCourseId());
                        t3.setText("资料类型:"+m1.getMaterialType());
                        t4.setText("资料号:"+m2.getId());
                        t5.setText("课程号:"+m2.getCourseId());
                        t6.setText("资料类型:"+m2.getMaterialType());
                        t7.setText("资料号:"+m3.getId());
                        t8.setText("课程号:"+m3.getCourseId());
                        t9.setText("资料类型:"+m3.getMaterialType());
                        t10.setText("资料号:"+m4.getId());
                        t11.setText("课程号:"+m4.getCourseId());
                        t12.setText("资料类型:"+m4.getMaterialType());
                    }
                    @Override
                    public void onFailure(Call<List<MaterialsList>> call, Throwable t) {

                    }

                    });
            }
        });

        mbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(materials.this, matmp4.class));
            }
        });
        mbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(materials.this, matimg.class));
            }
        });
        mbutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(materials.this, matmp3.class));
            }
        });
    }
}
