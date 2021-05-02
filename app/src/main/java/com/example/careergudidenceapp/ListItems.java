package com.example.careergudidenceapp;

public class ListItems{
    int image;
    String title, type, course;

    public ListItems(int image, String title, String type, String course) {
        this.image = image;
        this.title = title;
        this.type = type;
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public String getType() {
        return type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
