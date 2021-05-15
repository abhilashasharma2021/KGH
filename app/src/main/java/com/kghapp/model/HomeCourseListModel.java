package com.kghapp.model;

public class HomeCourseListModel {
    private String courseName;
    private String courseDuration;
    private String coursePrice;

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

    public int getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(int courseImage) {
        this.courseImage = courseImage;
    }

    public HomeCourseListModel(String courseName, String courseDuration, String coursePrice, String courseMedium, int courseImage) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.coursePrice = coursePrice;
        this.courseMedium = courseMedium;
        this.courseImage = courseImage;
    }

    private String courseMedium;
    private  int courseImage;
}
