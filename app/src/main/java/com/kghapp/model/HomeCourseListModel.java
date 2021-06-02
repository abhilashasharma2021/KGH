package com.kghapp.model;

public class HomeCourseListModel {
    private String courseName;
    private String courseDuration;
    private String coursePrice;
    private String courseMedium;
    private  String courseImage;
    private  String coursePath;
    private  String courseId;

    public String getOffline_cost() {
        return offline_cost;
    }

    public void setOffline_cost(String offline_cost) {
        this.offline_cost = offline_cost;
    }

    private  String offline_cost;

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    private  String courseType;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseMedium() {
        return courseMedium;
    }

    public void setCourseMedium(String courseMedium) {
        this.courseMedium = courseMedium;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCoursePath() {
        return coursePath;
    }

    public void setCoursePath(String coursePath) {
        this.coursePath = coursePath;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
