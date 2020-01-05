package com.example.app.GETMESSAGE;

import java.util.List;
import java.util.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetRequest_Interface {
    @HTTP(method = "GET", path = "courses", hasBody = false)
        Call<List<CourseList>> getCall();

    @HTTP(method = "GET", path = "courses/{id}", hasBody = false)
    Call<CourseList> getCall1(@Path("id") String id);

    @HTTP(method = "GET", path = "courses/{courseid}/photo", hasBody = false)
    Call<ResponseBody> getImg(@Path("courseid") String courseid);

    @HTTP(method = "GET", path = "teachers/{tid}/photo", hasBody = false)
    Call<ResponseBody> getTeaImg(@Path("tid") String tid);

    @HTTP(method = "GET", path = "courses/{courseid}/teachers", hasBody = false)
    Call<List<TeacherList>> getTea(@Path("courseid") String courseid);

    @HTTP(method = "GET", path = "courses/{courseid}/materials", hasBody = false)
    Call<List<MaterialsList>> getMeg(@Path("courseid") String courseid);

    @HTTP(method = "GET", path = "materials/{mid}/media", hasBody = false)
    Call<ResponseBody> getmp4(@Path("mid") String mid);

    @HTTP(method = "GET", path = "materials/{mid}/file", hasBody = false)
    Call<ResponseBody> getmegImg(@Path("mid") String mid);


}
