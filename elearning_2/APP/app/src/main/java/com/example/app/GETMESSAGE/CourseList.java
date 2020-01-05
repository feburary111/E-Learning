package com.example.app.GETMESSAGE;

import java.util.Date;

public class CourseList {
    private String id;
    private String name;
    private String code;
    private String categoryId;
    private String description;
    private String price;
    private String status;
    private Date openDate;
    private Date lastUpdateOn;
    private String level;
    private String shared;
    private String sharedUrl;
    private String avatar;
    private String bigAvatar;
    private String certification;
    private String certificationDuration;
    public CourseList(String id, String name, String code, String categoryId, String description, String price, String status, Date openDate, Date lastUpdateOn, String level, String shared, String sharedUrl, String avatar, String bigAvatar, String certification, String certificationDuration) {
        this.id=id;
        this.name=name;
        this.code=code;
        this.bigAvatar=bigAvatar;
        this.categoryId=categoryId;
        this.description=description;
        this.price=price;
        this.shared=shared;
        this.status=status;
        this.openDate=openDate;
        this.lastUpdateOn=lastUpdateOn;
        this.level=level;
        this.sharedUrl=sharedUrl;
        this.avatar=avatar;
        this.certification=certification;
        this.certificationDuration=certificationDuration;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBigAvatar(String bigAvatar) {
        this.bigAvatar = bigAvatar;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCertificationDuration(String certificationDuration) {
        this.certificationDuration = certificationDuration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public void setSharedUrl(String sharedUrl) {
        this.sharedUrl = sharedUrl;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getId() {
        return id;
    }

    public String getBigAvatar() {
        return bigAvatar;
    }

    public String getCertification() {
        return certification;
    }

    public String getCertificationDuration() {
        return certificationDuration;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    public String getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public String getPrice() {
        return price;
    }

    public String getShared() {
        return shared;
    }

    public String getSharedUrl() {
        return sharedUrl;
    }

    public String getStatus() {
        return status;
    }

    public String courseInfo(CourseList result){
        String s1=result.getId();
        String s2=result.getName();
        String s3=result.getCode();
        String s4=result.getCategoryId();
        String s5=result.getDescription();
        String s6=result.getPrice();
        String s7=result.getStatus();
        Date s8=result.getOpenDate();
        Date s9=result.getLastUpdateOn();
        String s10=result.getLevel();
        String s11=result.getShared();
        String s12=result.getSharedUrl();
        String s13=result.getAvatar();
        String s14=result.getBigAvatar();
        String s15=result.getCertification();
        String s16=result.getCertificationDuration();
        return("课号:"+s1+"\n课程名:"+s2+"\n课码:"+s3+"\n种类:"+s4+"\n描述:"+s5+"\n价格:"+s6+"\n状态:"+s7+"\n开课时间:"+s8+"\n结课时间:"+s9+"\n水准:"+s10+"\n分享:"+s11+"\n分享url:"+s12+"\n头像:"+s13+"\n大头像:"+s14+"\n鉴定:"+s15+"\n核证:"+s16+"\n");
    }
}

