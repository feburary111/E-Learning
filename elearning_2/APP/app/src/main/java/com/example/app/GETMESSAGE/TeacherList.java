package com.example.app.GETMESSAGE;

import java.util.Date;

public class TeacherList {
    private String userid;
    private String courseId;
    private String name;
    private String telephone;
    private String email;
    private String description;
    public TeacherList(String userid,String courseId,String name,String telephone,String email,String description){
        this.courseId=courseId;
        this.userid=userid;
        this.name=name;
        this.telephone=telephone;
        this.email=email;
        this.description=description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getUserid() {
        return userid;
    }
    public String TEAInfo(TeacherList result){
        String s1=result.getUserid();
        String s2=result.getCourseId();
        String s3=result.getName();
        String s4=result.getTelephone();
        String s5=result.getEmail();
        String s6=result.getDescription();
        return("教工号:"+s1+"\n课程号:"+s2+"\n姓名:"+s3+"\n电话:"+s4+"\n邮箱:"+s5+"\n描述:"+s6);
    }
}
