package com.example.app.GETMESSAGE;

import java.util.Date;

public class MaterialsList {
    private String id;
    private String courseId;
    private String status;
    private String mediatype;
    private String materialType;
    private String materialUrl;
    private Date createDate;
    private String description;
    public MaterialsList(String id,String courseId,String mediatype,String materialType,String materialUrl,Date createDate, String description,String status){
        this.courseId=courseId;
        this.id=id;
        this.description=description;
        this.createDate=createDate;
        this.materialType=materialType;
        this.mediatype=mediatype;
        this.materialUrl=materialUrl;
        this.status=status;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getMaterialType() {
        return materialType;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public String getMediatype() {
        return mediatype;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }

}
