package com.example.app.BOTTOMUI;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.GETMESSAGE.CourseList;
import com.example.app.GETMESSAGE.GetRequest_Interface;
import com.example.app.GETMESSAGE.TeacherList;
import com.example.app.R;
import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class teacher extends AppCompatActivity {
    private EditText medit;
    private ImageView img;
    private Button buttontea;
    private TextView mtv;
    public List<TeacherList> mteaList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacherui);
        medit = findViewById(R.id.editText2);
        img=findViewById(R.id.imageView6);
        mtv=findViewById(R.id.textView6);
        buttontea=findViewById(R.id.buttontea2);
        buttontea.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        //注意这里是请求的主机名,必须以/结尾
                        .baseUrl("http://10.0.2.2:8080/elearn/")
                        .build();
                // 步骤5:创建 网络请求接口 的实
                final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
                Call<ResponseBody> call = request.getTeaImg(medit.getText().toString().substring(medit.getText().toString().length()-1));
                call.enqueue(new Callback<ResponseBody> (){
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        InputStream inputStream = response.body().byteStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        img.setImageBitmap(bitmap);
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                    }
                });


                Retrofit retrofit2= new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        //注意这里是请求的主机名,必须以/结尾
                        .baseUrl("http://10.0.2.2:8080/elearn/")
                        .build();
                // 步骤5:创建 网络请求接口 的实例
                final GetRequest_Interface request2 = retrofit2.create(GetRequest_Interface.class);
                //对 发送请求 进行封装
                Call<List<TeacherList>> call2 = request2.getTea(medit.getText().toString());
                //步骤6:发送网络请求(异步)
                call2.enqueue(new Callback<List<TeacherList>>() {
                    @Override
                    public void onResponse(Call<List<TeacherList>> call, Response<List<TeacherList>> response) {
                        mteaList=response.body();
                        for(int i=0;i<mteaList.size();i++)
                        {
                            TeacherList result=mteaList.get(i);
                            mtv.append(result.TEAInfo(result));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TeacherList>> call, Throwable t) {

                    }});


            }
        });
    }


    }


