package com.example.app.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app.GETMESSAGE.CourseList;
import com.example.app.GETMESSAGE.GetRequest_Interface;
import com.example.app.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class courseAdapter extends RecyclerView.Adapter<courseAdapter.ViewHolder>  {
    private List<CourseList> mcourseList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView CourseImage;
        TextView CourseName;
        TextView CourseId;
        public ViewHolder(View view) {
            super(view);
            CourseImage = (ImageView) view.findViewById(R.id.imageView3);
            CourseId=view.findViewById(R.id.textView16);
            CourseName = (TextView) view.findViewById(R.id.textView17);
        }
    }
    public courseAdapter (List<CourseList> courseList){
        mcourseList = new ArrayList<CourseList>();
        mcourseList.addAll(courseList);
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_dome,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CourseList c1 = mcourseList.get(position);
        holder.CourseName.setText("课程号："+c1.getName());
        holder.CourseId.setText("课程名："+c1.getId());
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                //注意这里是请求的主机名,必须以/结尾
                .baseUrl("http://10.0.2.2:8080/elearn/")
                .build();
        // 步骤5:创建 网络请求接口 的实
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        Call<ResponseBody> call = request.getImg("001");
        call.enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                InputStream inputStream = response.body().byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                holder.CourseImage.setImageBitmap(bitmap);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mcourseList.size();
    }
}
