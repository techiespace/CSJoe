package com.techiespace.projects.csjoe;

import com.google.firebase.firestore.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Course {

    private String course_name;

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
}