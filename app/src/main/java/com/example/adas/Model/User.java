package com.example.adas.Model;

import java.util.Date;

public class User {
    private String userId;
    private String name;
    private String address;
    private Date createDate;
    private String assessmentPlace;

    public User() {

    }

    public User(String userId, String name, String address, Date createDate, String assessmentPlace) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.createDate = createDate;
        this.assessmentPlace = assessmentPlace;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAssessmentPlace() {
        return assessmentPlace;
    }

    public void setAssessmentPlace(String assessmentPlace) {
        this.assessmentPlace = assessmentPlace;
    }
}
