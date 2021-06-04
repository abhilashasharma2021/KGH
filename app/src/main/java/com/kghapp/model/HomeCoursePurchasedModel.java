package com.kghapp.model;

public class HomeCoursePurchasedModel {
    private String courseName;
    private String courseMedium;
    private  String courseImage;
    private  String coursePath;
    private  String courseId;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
